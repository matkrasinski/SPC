package cloud.lambdas.handlers.serviceHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindServiceByIdHandler implements RequestHandler<Long, Service> {
    ServiceService serviceService = new ServiceService();

    @Override
    public Service handleRequest(Long id, Context context) {
        return serviceService.findServiceById(id);
    }
}
