package cloud.lambdas.service;

import cloud.lambdas.pojo.City;
import cloud.lambdas.pojo.Company;
import cloud.lambdas.pojo.Service;
import cloud.lambdas.repository.CityRepository;

import java.util.List;

public class CityService {
    private final CityRepository cityRepository = new CityRepository();

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    public List<City> getCitiesByCompany(Long companyId) {
        return cityRepository.getCitiesByCompany(companyId);
    }

    public City findCityById(Long id) {
        return cityRepository.findCityById(id);
    }

    public City findCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    public City findCityByCompanyIdAndName(Long companyId, String name) {return
            cityRepository.findCityByCompanyIdAndName(companyId,name);}


    public boolean addCity(String cityName, Long companyId) {
        return cityRepository.addCity(cityName, companyId);
    }

    public boolean deleteCity(Long cityId) {
        return cityRepository.deleteCity(cityId);
    }

}
