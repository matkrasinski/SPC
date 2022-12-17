package cloud.lambdas.service;

import cloud.lambdas.pojo.City;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.CityRepository;

import java.util.List;

public class CityService {
    CityRepository cityRepository = new CityRepository();

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }



    public City findCityById(Long id) {
        return cityRepository.findCityById(id);
    }

    public City findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }


    public boolean addCity(City city) {
        return cityRepository.addCity(city);
    }

    public boolean deleteCity(Long cityId) {
        return cityRepository.deleteCity(cityId);
    }

}
