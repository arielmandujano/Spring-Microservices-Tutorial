package com.arielmandujano.usermicroservice.service;

import com.arielmandujano.usermicroservice.config.BikeConstants;
import com.arielmandujano.usermicroservice.config.CarConstants;
import com.arielmandujano.usermicroservice.dtos.FullInformation;
import com.arielmandujano.usermicroservice.entity.User;
import com.arielmandujano.usermicroservice.feignclients.BikeFeignClient;
import com.arielmandujano.usermicroservice.feignclients.CarFeignClient;
import com.arielmandujano.usermicroservice.models.Bike;
import com.arielmandujano.usermicroservice.models.Car;
import com.arielmandujano.usermicroservice.models.Vehicle;
import com.arielmandujano.usermicroservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Car> getCars(int id) {
        List<Car> cars = restTemplate.getForObject(String.format(CarConstants.GET_ALL_CARS_BY_USER_ID, id), List.class);
        return cars;
    }

    public List<Bike> getBikes(int id) {
        List<Bike> bikes = restTemplate.getForObject(String.format(BikeConstants.GET_ALL_BIKES_BY_USER_ID, id), List.class);
        return bikes;
    }

    public List<Vehicle> getVehicles(int id) {
        List<Car> cars = getCars(id);
        List<Bike> bikes = getBikes(id);
        List<Vehicle> vehicles= new LinkedList<>();
        if(!cars.isEmpty()) {
            cars.forEach(car -> vehicles.add(car));
        }
        if(!bikes.isEmpty()) {
            bikes.forEach(bike -> vehicles.add(bike));
        }
        return vehicles;
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public FullInformation getUserAndVehicles(int userId) {
        Map<String, List<Vehicle>> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return new FullInformation(null, null);
        }
        List<Car> cars = carFeignClient.carsByUserId(userId);
        List<Bike> bikes = bikeFeignClient.bikesByUserId(userId);
        List<Vehicle> carsAsVehicles;
        List<Vehicle> bikesAsVehicles;
        if(cars == null) {
            carsAsVehicles = new ArrayList<>();
        } else {
            carsAsVehicles = cars.stream().map(car -> (Vehicle)car).toList();
        }
        if(bikes==null) {
            bikesAsVehicles = new ArrayList<>();
        } else {
            bikesAsVehicles = bikes.stream().map(bike -> (Vehicle)bike).toList();
        }
        result.put("cars", carsAsVehicles);
        result.put("bikes", bikesAsVehicles);
        return new FullInformation(user, result);
    }
}
