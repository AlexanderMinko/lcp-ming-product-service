package com.lenovo.productservice.entity.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateCategoryParam {

  @NotBlank
  @Size(max = 20)
  private String name;

  @NotBlank
  @Size(max = 30)
  private String displayName;

  @Size(max = 1024)
  private String description;
}
