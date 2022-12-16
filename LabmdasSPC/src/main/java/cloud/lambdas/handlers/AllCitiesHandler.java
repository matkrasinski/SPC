package cloud.lambdas.handlers;

import cloud.lambdas.model.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class AllCitiesHandler implements RequestHandler<Object, List<City>> {
    CityService cityService = new CityService();

    @Override
    public List<City> handleRequest(Object o, Context context) {
        return cityService.getAllCities();
    }
}
