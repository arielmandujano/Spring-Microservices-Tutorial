package com.arielmandujano.usermicroservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car implements Vehicle{
    private String brand;
    private String model;
    private int userId;
}
