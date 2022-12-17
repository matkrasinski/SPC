package cloud.lambdas.handlers.cityHandlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindCityByNameHandler implements RequestHandler<String, City> {
    CityService cityService = new CityService();

    @Override
    public City handleRequest(String s, Context context) {
        return cityService.findCityByName(s);
    }
}
