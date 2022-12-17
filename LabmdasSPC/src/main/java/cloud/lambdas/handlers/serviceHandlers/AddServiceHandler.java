package cloud.lambdas.handlers.serviceHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddServiceHandler implements RequestHandler<Service, Boolean> {

    ServiceService serviceService = new ServiceService();
    @Override
    public Boolean handleRequest(Service service, Context context) {
       return serviceService.addService(service.getName());

    }
}
