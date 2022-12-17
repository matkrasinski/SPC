package cloud.lambdas.handlers.companyHandlers;

import cloud.lambdas.service.CompanyService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteCompanyHandler implements RequestHandler<Long,Boolean> {
    CompanyService companyService = new CompanyService();

    @Override
    public Boolean handleRequest(Long companyId, Context context) {
        return companyService.deleteCompany(companyId);
    }
}
