package cloud.lambdas.handlers.userHandlers;

import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddUserHandler implements RequestHandler<User, Boolean> {
    UserService userService = new UserService();

    @Override
    public Boolean handleRequest(User user, Context context) {
        return userService.addUser(user);

    }
}
