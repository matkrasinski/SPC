package cloud.lambdas.handlers.companyUserHandlers;

import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class FindUserServicesHandler implements RequestHandler<Long, List<ServiceDto>> {

    UserService userService = new UserService();

    @Override
    public List<ServiceDto> handleRequest(Long id, Context context) {
        return userService.findUserServices(id);
    }
}
