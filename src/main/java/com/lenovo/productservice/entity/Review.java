package com.lenovo.productservice.entity;

import com.lenovo.productservice.entity.dto.ReviewRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Review {

    @Id
    private String id;
    private String content;
    private Instant createdDate;
    private String accountId;
    private String productId;
    private String parentId;

    public Review(ReviewRequestDto dto) {
        this.id = UUID.randomUUID().toString();
        this.content = dto.getContent();
        this.createdDate = Instant.now();
        this.accountId = dto.getAccountId();
        this.productId = dto.getProductId();
        this.parentId = dto.getParentId();
    }

}
