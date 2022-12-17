package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class AllCompaniesHandler {
    CompanyService companyService = new CompanyService();

    public List<Company> handleRequest(Context context) {
        return companyService.getAllCompanies();
    }
}
