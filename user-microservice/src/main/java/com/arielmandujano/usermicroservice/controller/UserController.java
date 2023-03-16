package com.arielmandujano.usermicroservice.controller;

import com.arielmandujano.usermicroservice.dtos.FullInformation;
import com.arielmandujano.usermicroservice.entity.User;
import com.arielmandujano.usermicroservice.models.Bike;
import com.arielmandujano.usermicroservice.models.Car;
import com.arielmandujano.usermicroservice.models.Vehicle;
import com.arielmandujano.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user) {
        User userNew = userService.save(user);
        if(userNew == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(userNew);
        }
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(@PathVariable("userId") int userId) {
        List<Car> cars = userService.getCars(userId);
        if(cars.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(cars);
        }
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikesByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = userService.getBikes(userId);
        if(bikes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bikes);
        }
    }
    @GetMapping("/vehicles/{userId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUserId(@PathVariable("userId") int userId) {
        List<Vehicle> vehicles = userService.getVehicles(userId);
        if(vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vehicles);
        }
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Car carNew = userService.saveCar(userId, car);
        if(carNew == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(carNew);
        }
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity<Bike> saveCar(@PathVariable("userId") int userId, @RequestBody Bike bike) {
        if (userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        Bike bikeNew = userService.saveBike(userId, bike);
        if(bikeNew == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(bikeNew);
        }
    }

    @GetMapping("/getuserandvehicles/{userId}")
    public ResponseEntity<FullInformation> getFullInformation(@PathVariable("userId") int userId) {
        if(userService.getUserById(userId) == null) {
            return ResponseEntity.notFound().build();
        }
        FullInformation fullInformation = userService.getUserAndVehicles(userId);
        return ResponseEntity.ok(fullInformation);
    }
}
