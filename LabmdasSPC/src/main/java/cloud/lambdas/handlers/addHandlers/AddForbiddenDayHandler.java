package cloud.lambdas.handlers.addHandlers;

import cloud.lambdas.pojo.Day;
import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddForbiddenDayHandler implements RequestHandler<Day, Day> {
    CompanyService companyService = new CompanyService();

    @Override
    public Day handleRequest(Day day, Context context) {
        return companyService.addForbiddenDay(companyService.findCompanyById(day.getId()).getName(),
                day.getForbiddenDay());
    }

}
