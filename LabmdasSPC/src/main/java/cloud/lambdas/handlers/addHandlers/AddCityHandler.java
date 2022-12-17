package cloud.lambdas.handlers.addHandlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddCityHandler implements RequestHandler<City, City> {

    CityService cityService = new CityService();

    @Override
    public City handleRequest(City city, Context context) {
        cityService.addCity(city.getName(), city.getId());
        return cityService.findCityByName(city.getName());
    }
}
