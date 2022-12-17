package cloud.lambdas.handlers.cityHandlers;

import cloud.lambdas.pojo.City;
import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindCityByIdHandler implements RequestHandler<Long, City> {
    CityService cityService = new CityService();

    @Override
    public City handleRequest(Long id, Context context) {
        return cityService.findCityById(id);
    }
}
