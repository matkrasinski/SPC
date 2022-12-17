package cloud.lambdas.handlers.userHandlers;

import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindUserByIdHandler implements RequestHandler<Long, User> {
    UserService userService = new UserService();

    @Override
    public User handleRequest(Long id, Context context) {
        return userService.findUserById(id);
    }
}
