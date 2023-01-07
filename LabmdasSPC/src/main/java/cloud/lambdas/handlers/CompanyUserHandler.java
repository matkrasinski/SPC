package cloud.lambdas.handlers;

import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;

public class CompanyUserHandler {
    UserService userService = new UserService();

//    public ServiceDto handleAddServiceToUserRequest(CompanyUser companyUser, Context context) {
//        return userService.addServiceToUser(companyUser);
//    }

    public CompanyUser handleFindCompanyUserByIdRequest(Long id, Context context) {
        return userService.findCompanyUserById(id);
    }

//    public List<ServiceDto> handleFindUserServicesRequest(Long id, Context context) {
//        return userService.findUserServices(id);
//    }

}
