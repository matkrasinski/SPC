package cloud.lambdas.handlers;

import cloud.lambdas.pojo.Day;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class DayHandler {
    CompanyService companyService = new CompanyService();

    public Day handleAddForbiddenDayRequest(Day day, Context context) {
        return companyService.addForbiddenDay(day);
    }

    public List<Day> handleGetCompanyForbiddenDaysRequest(Long companyId, Context context) {
        return companyService.getCompanyForbiddenDays(companyId);
    }

}
