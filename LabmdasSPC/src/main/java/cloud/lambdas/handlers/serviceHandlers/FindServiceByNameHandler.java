package cloud.lambdas.handlers.serviceHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindServiceByNameHandler implements RequestHandler<String, Service> {
    ServiceService serviceService = new ServiceService();

    @Override
    public Service handleRequest(String s, Context context) {
        return serviceService.findServiceByName(s);
    }
}
