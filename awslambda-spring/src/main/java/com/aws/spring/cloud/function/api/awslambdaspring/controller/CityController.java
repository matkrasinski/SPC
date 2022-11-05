package com.aws.spring.cloud.function.api.awslambdaspring.controller;

import com.aws.spring.cloud.function.api.awslambdaspring.entity.City;
import com.aws.spring.cloud.function.api.awslambdaspring.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("")
    public List<City> getAll() {
        return cityRepository.getAll();
    }
    @GetMapping("/{id}")
    public City getById(@PathVariable("id") int id) {
        return cityRepository.getById(id);
    }
}
