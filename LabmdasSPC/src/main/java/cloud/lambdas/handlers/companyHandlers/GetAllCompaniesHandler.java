package cloud.lambdas.handlers.companyHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetAllCompaniesHandler implements RequestHandler<Void,List<Company>> {
    CompanyService companyService = new CompanyService();


    @Override
    public List<Company> handleRequest(Void unused, Context context) {
        return companyService.getAllCompanies();
    }
}
