package com.lenovo.productservice.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private String id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String photoUrl;

}
