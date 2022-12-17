package cloud.lambdas.handlers.companyHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindCompanyByIdHandler implements RequestHandler<Long, Company> {
    CompanyService companyService = new CompanyService();

    @Override
    public Company handleRequest(Long id, Context context) {
        return companyService.findCompanyById(id);
    }
}
