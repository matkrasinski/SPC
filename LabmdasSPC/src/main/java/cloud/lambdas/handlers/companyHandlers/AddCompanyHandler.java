package cloud.lambdas.handlers.companyHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddCompanyHandler implements RequestHandler<Company, Boolean> {
    CompanyService companyService = new CompanyService();

    @Override
    public Boolean handleRequest(Company company, Context context) {
        return companyService.addCompany(company);
    }
}
