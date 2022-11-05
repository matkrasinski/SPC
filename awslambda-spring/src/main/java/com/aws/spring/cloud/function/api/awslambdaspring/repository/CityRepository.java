package com.aws.spring.cloud.function.api.awslambdaspring.repository;

import com.aws.spring.cloud.function.api.awslambdaspring.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class CityRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<City> getAll() {
        return jdbcTemplate.query("SELECT id, name FROM City", BeanPropertyRowMapper.newInstance(City.class));
    }
    public City getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name FROM City WHERE " + "id = ?",  BeanPropertyRowMapper.newInstance(City.class), id);
    }
}
