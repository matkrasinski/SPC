package cloud.lambdas.handlers.serviceHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class FindCompanyServicesHandler implements RequestHandler<Long, List<Service>> {
    CompanyService companyService = new CompanyService();

    @Override
    public List<Service> handleRequest(Long companyId, Context context) {
        return companyService.getCompanyServices(companyId);
    }
}
