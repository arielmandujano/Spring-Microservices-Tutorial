package com.arielmandujano.usermicroservice.dtos;

import com.arielmandujano.usermicroservice.entity.User;
import com.arielmandujano.usermicroservice.models.Vehicle;

import java.util.List;
import java.util.Map;

public record FullInformation(
        User user,
        Map<String, List<Vehicle>> vehicles
) {
}
