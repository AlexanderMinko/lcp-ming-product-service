package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LANGUAGE = "en";
    private static final String PRODUCT_ID = "_id";

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Product> findByNameContains(String freeText, Pageable pageable) {
        return executeQuery(createCriteria(freeText), pageable);
    }

    public Page<Product> executeQuery(CriteriaDefinition criteria, Pageable pageable) {
        var aggregationOperations = new ArrayList<>(List.of(
                Aggregation.match(criteria),
                Aggregation.skip(pageable.getOffset()),
                Aggregation.limit(pageable.getPageSize())));

        Optional.of(pageable.getSort())
                .filter(orders -> !orders.isUnsorted())
                .ifPresent(sort -> aggregationOperations.add(Aggregation.sort(sort)));

        var aggregation = Aggregation.newAggregation(aggregationOperations);

        aggregation = aggregation.withOptions(AggregationOptions.builder()
                .collation(Collation.of(LANGUAGE).strength(Collation.ComparisonLevel.secondary()))
                .build());

        var aggregationResults = mongoTemplate.aggregate(aggregation, "product", Product.class)
                .getMappedResults();

        var countAggregation = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.project(PRODUCT_ID));

        var totalMatchedProducts = mongoTemplate.aggregate(countAggregation, "product", Product.class)
                .getMappedResults()
                .size();

        return new PageImpl<>(aggregationResults, pageable, totalMatchedProducts);
    }

    private CriteriaDefinition createCriteria(String freeText) {
        List<Criteria> criteriaList = new ArrayList<>();
        final var criteria = buildFullTextSearchCriteria(freeText);
        criteriaList.add(new Criteria().orOperator(criteria.toArray(new Criteria[0])));
        return new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    }

    private List<Criteria> buildFullTextSearchCriteria(final String freeText) {
        final List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where(NAME).regex(getRegex(freeText), "i"));
        criteria.add(Criteria.where(DESCRIPTION).regex(getRegex(freeText), "i"));
        return criteria;
    }

    private String getRegex(final String field) {
        return MongoRegexCreator.INSTANCE.toRegularExpression(field, MongoRegexCreator.MatchMode.CONTAINING);
    }
}
