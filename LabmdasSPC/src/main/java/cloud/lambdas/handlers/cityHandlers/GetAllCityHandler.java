package cloud.lambdas.handlers.cityHandlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetAllCityHandler implements RequestHandler<Void, List<City>>{

    CityService cityService = new CityService();

    @Override
    public List<City> handleRequest(Void unused, Context context) {
        return cityService.getAllCities();
    }
}
