package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.Company;
import cloud.lambdas.service.CityService;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class FindCompaniesInCityHandler implements RequestHandler<String, List<Company>> {

    CompanyService companyService = new CompanyService();

    @Override
    public List<Company> handleRequest(String cityName, Context context) {
        return companyService.getCompaniesInCity(cityName);
    }

}
