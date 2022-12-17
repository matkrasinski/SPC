package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class FindServicesInCityHandler implements RequestHandler<String, List<Service>> {

    ServiceService serviceService = new ServiceService();

    @Override
    public List<Service> handleRequest(String cityName, Context context) {
        return serviceService.getServicesInCity(cityName);
    }


}
