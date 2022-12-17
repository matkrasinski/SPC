package cloud.lambdas.handlers.addHandlers;

import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddServiceToUserHandler implements RequestHandler<CompanyUser, ServiceDto> {
    UserService userService = new UserService();

    @Override
    public ServiceDto handleRequest(CompanyUser companyUser, Context context) {
        return userService.addServiceToUser(userService.findById(companyUser.getUserId()).getEmail(), companyUser.getCompanyId(),
                companyUser.getServiceId(), companyUser.getOrderDate(), companyUser.getDescription());
    }
}
