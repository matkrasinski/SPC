package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.Service;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class FindCompanyServicesHandler implements RequestHandler<String, List<Service>> {
    CompanyService companyService = new CompanyService();

    @Override
    public List<Service> handleRequest(String companyName, Context context) {
        return companyService.getCompanyServices(companyName);
    }
}
