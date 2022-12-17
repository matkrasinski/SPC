package cloud.lambdas.handlers.cityHandlers;

import cloud.lambdas.service.CityService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteCityHandler implements RequestHandler<Long,Boolean> {

    CityService cityService = new CityService();


    @Override
    public Boolean handleRequest(Long cityId, Context context) {
        return cityService.deleteCity(cityId);
    }
}
