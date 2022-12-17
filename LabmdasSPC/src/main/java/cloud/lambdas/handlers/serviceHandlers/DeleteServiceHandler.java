package cloud.lambdas.handlers.serviceHandlers;

import cloud.lambdas.service.ServiceService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteServiceHandler implements RequestHandler<Long,Boolean> {

    ServiceService serviceService = new ServiceService();
    @Override
    public Boolean handleRequest(Long serviceId, Context context) {
        return serviceService.deleteService(serviceId);
    }
}

