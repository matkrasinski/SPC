package cloud.lambdas.handlers.addHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddCompanyHandler implements RequestHandler<Company, Company> {
    CompanyService companyService = new CompanyService();
    @Override
    public Company handleRequest(Company company, Context context) {
        companyService.addCompany(company.getName());
        return companyService.findCompanyByName(company.getName());
    }
}
