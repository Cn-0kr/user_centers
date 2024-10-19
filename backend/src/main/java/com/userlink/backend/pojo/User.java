package com.userlink.backend.pojo;/*
 * @author  0kr
 * @version 1.0
 */

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}