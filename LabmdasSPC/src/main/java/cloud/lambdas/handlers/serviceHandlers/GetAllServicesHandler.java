package cloud.lambdas.handlers.serviceHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetAllServicesHandler implements RequestHandler<Void,List<Service>> {

    ServiceService serviceService = new ServiceService();


    @Override
    public List<Service> handleRequest(Void unused, Context context) {
        return serviceService.getAllServices();
    }
}
