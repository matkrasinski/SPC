package cloud.lambdas.handlers.cityHandlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddCityHandler implements RequestHandler<City, Boolean> {

    CityService cityService = new CityService();

    @Override
    public Boolean handleRequest(City city, Context context) {
       return cityService.addCity(city.getName(), city.getCompanyId());

    }
}
