package cloud.lambdas.handlers.getHandlers;

import cloud.lambdas.dto.ServiceDto;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class FindUserServicesHandler implements RequestHandler<String, List<ServiceDto>> {

    UserService userService = new UserService();

    @Override
    public List<ServiceDto> handleRequest(String s, Context context) {
        return userService.findUserServices(s);
    }
}
