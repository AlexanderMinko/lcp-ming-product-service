package com.lenovo.productservice.repository;

import com.lenovo.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String CATEGORY_ID = "categoryId";
    private static final String LANGUAGE = "en";
    private static final String PRODUCT_ID = "_id";

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Product> findBySearchParams(String freeText, String categoryId, String producerId, Pageable pageable) {
        return executeQuery(createCriteria(freeText, categoryId, producerId), pageable);
    }

    public Page<Product> executeQuery(CriteriaDefinition criteria, Pageable pageable) {
        var content = mongoTemplate.find(query(criteria).with(pageable), Product.class);
        return PageableExecutionUtils.getPage(content, pageable, () -> mongoTemplate.count(query(criteria), Product.class));
    }

    private CriteriaDefinition createCriteria(String freeText, String categoryId, String producerId) {
        var criteriaList = new ArrayList<>();
        if(isNotEmpty(freeText)) {
            var textSearchCriteria = buildFullTextSearchCriteria(freeText);
            criteriaList.add(new Criteria().orOperator(textSearchCriteria.toArray(new Criteria[0])));
        }
        if (isNotEmpty(categoryId)) {
            criteriaList.add(Criteria.where(CATEGORY_ID).is(categoryId));
        }
        if (isNotEmpty(producerId)) {
            criteriaList.add(Criteria.where(CATEGORY_ID).is(categoryId));
        }
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
