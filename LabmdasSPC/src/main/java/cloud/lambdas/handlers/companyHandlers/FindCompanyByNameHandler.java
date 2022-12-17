package cloud.lambdas.handlers.companyHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindCompanyByNameHandler implements RequestHandler<String, Company> {
    CompanyService companyService = new CompanyService();

    @Override
    public Company handleRequest(String s, Context context) {
        return companyService.findCompanyByName(s);
    }
}
