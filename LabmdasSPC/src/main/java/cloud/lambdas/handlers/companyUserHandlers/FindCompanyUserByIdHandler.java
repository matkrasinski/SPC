package cloud.lambdas.handlers.companyUserHandlers;

import cloud.lambdas.pojo.CompanyUser;
import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindCompanyUserByIdHandler implements RequestHandler<Long, CompanyUser> {
    UserService userService = new UserService();

    @Override
    public CompanyUser handleRequest(Long id, Context context) {
        return userService.findCompanyUserById(id);
    }
}
