package cloud.lambdas.service;

import cloud.lambdas.model.City;
import cloud.lambdas.model.Company;
import cloud.lambdas.model.Service;
import cloud.lambdas.repository.CityRepository;

import java.util.List;

public class CityService {
    CityRepository cityRepository = new CityRepository();

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    public List<Company> getCompaniesInCity(String cityName) {
        return cityRepository.getCompaniesByCity(cityName);
    }

    public List<Service> getServicesInCity(String cityName) {
        return cityRepository.getServicesByCity(cityName);
    }


    public boolean addCity(String cityName, Integer companyId) {
        return cityRepository.addCity(cityName, companyId);
    }

    public boolean deleteCity(Integer cityId) {
        return cityRepository.deleteUser(cityId);
    }

}
