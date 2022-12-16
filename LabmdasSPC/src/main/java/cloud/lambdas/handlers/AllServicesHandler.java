package cloud.lambdas.handlers;

import cloud.lambdas.model.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class AllServicesHandler implements RequestHandler<Object, List<Service>> {

    ServiceService serviceService = new ServiceService();

    @Override
    public List<Service> handleRequest(Object o, Context context) {
        return serviceService.getAllServices();
    }
}
