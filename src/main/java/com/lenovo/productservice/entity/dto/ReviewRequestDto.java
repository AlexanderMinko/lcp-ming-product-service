package com.lenovo.productservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequestDto {

    private String content;
    private String accountId;
    private String productId;
    private String parentId;

}
