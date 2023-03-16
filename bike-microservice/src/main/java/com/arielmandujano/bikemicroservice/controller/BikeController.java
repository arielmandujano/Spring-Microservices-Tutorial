package com.arielmandujano.bikemicroservice.controller;

import com.arielmandujano.bikemicroservice.entity.Bike;
import com.arielmandujano.bikemicroservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping()
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bikes);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getById(@PathVariable("id") int id) {
        Bike bike = bikeService.getById(id);
        if(bike == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bike);
        }
    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.save(bike);
        if(bikeNew == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(bikeNew);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bike>> getAllByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.getAllByUserId(userId);
        if(bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bikes);
        }
    }
}
