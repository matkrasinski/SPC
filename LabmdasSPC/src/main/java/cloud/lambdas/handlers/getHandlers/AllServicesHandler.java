package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class AllServicesHandler {

    ServiceService serviceService = new ServiceService();

    public List<Service> handleRequest(Context context) {
        return serviceService.getAllServices();
    }
}
