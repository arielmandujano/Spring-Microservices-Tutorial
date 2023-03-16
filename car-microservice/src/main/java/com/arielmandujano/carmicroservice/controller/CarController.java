package com.arielmandujano.carmicroservice.controller;

import com.arielmandujano.carmicroservice.entity.Car;
import com.arielmandujano.carmicroservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping()
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();
        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cars);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable("id") int id) {
        Car car = carService.getById(id);
        if(car == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(car);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> cars = carService.findByUserId(userId);
        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cars);
        }
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car) {
        Car carNew = carService.save(car);
        if(carNew == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(carNew);
        }
    }
}
