package com.arielmandujano.bikemicroservice.service;

import com.arielmandujano.bikemicroservice.entity.Bike;
import com.arielmandujano.bikemicroservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public List<Bike> getAllByUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }

    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }
}
