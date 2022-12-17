package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class AllCitiesHandler {
    CityService cityService = new CityService();

    public List<City> handleRequest(Context context) {
        return cityService.getAllCities();
    }
}
