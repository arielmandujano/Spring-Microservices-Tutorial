package com.arielmandujano.usermicroservice.feignclients;

import com.arielmandujano.usermicroservice.config.CarConstants;
import com.arielmandujano.usermicroservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "car-service", url = CarConstants.ROOT)
public interface CarFeignClient {

    @PostMapping()
    Car save(@RequestBody Car car);

    @GetMapping("/user/{userId}")
    List<Car> carsByUserId(@PathVariable("userId") int userId);

}
