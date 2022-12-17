package cloud.lambdas.handlers.dayHandlers;

import cloud.lambdas.pojo.Day;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetCompanyForbiddenDaysHandler implements RequestHandler<Long, List<Day>> {
    CompanyService companyService = new CompanyService();

    @Override
    public List<Day> handleRequest(Long companyId, Context context) {
        return companyService.getCompanyForbiddenDays(companyId);
    }


}
