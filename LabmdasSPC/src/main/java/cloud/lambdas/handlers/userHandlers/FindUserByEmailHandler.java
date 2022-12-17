package cloud.lambdas.handlers.userHandlers;

import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class FindUserByEmailHandler implements RequestHandler<String, User> {
    UserService userService = new UserService();

    @Override
    public User handleRequest(String s, Context context) {
        return userService.findUserByEmail(s);
    }
}
