package cloud.lambdas.handlers.addHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddServiceHandler implements RequestHandler<Service, Service> {

    ServiceService serviceService = new ServiceService();
    @Override
    public Service handleRequest(Service service, Context context) {
        serviceService.addService(service.getName());
        return serviceService.findServiceByName(service.getName());
    }
}
