package cloud.lambdas.handlers;

import cloud.lambdas.model.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class AllCompaniesHandler implements RequestHandler<Object, List<Company>> {
    CompanyService companyService = new CompanyService();

    @Override
    public List<Company> handleRequest(Object o, Context context) {
        return companyService.getAllCompanies();
    }
}
