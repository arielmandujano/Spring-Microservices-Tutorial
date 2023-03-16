package com.arielmandujano.usermicroservice.feignclients;

import com.arielmandujano.usermicroservice.config.BikeConstants;
import com.arielmandujano.usermicroservice.models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "bike-service", url = BikeConstants.ROOT)
public interface BikeFeignClient {
    @PostMapping()
    Bike save(@RequestBody Bike bike);

    @GetMapping("/user/{userId}")
    List<Bike> bikesByUserId(@PathVariable("userId") int userId);
}
