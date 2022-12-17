package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.pojo.Day;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetCompanyForbiddenDaysHandler implements RequestHandler<String, List<Day>> {
    CompanyService companyService = new CompanyService();

    @Override
    public List<Day> handleRequest(String companyName, Context context) {
        return companyService.getCompanyForbiddenDays(companyName);
    }


}
