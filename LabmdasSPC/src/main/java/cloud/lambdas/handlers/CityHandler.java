package cloud.lambdas.handlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class CityHandler {

    CityService cityService = new CityService();

    public Boolean handleAddCityRequest(City city, Context context) {
        return cityService.addCity(city.getName(), city.getCompanyId());
    }

    public Boolean handleDeleteCityRequest(Long cityId, Context context) {
        return cityService.deleteCity(cityId);
    }

    public City handleFindByIdRequest(Long id, Context context) {
        return cityService.findCityById(id);
    }

    public City handleFindByNameRequest(String s, Context context) {
        return cityService.findCityByName(s);
    }

    public List<City> handleGetAllCitiesRequest(Context context) {
        return cityService.getAllCities();
    }
}
