package cloud.lambdas.handlers;


import cloud.lambdas.model.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class AllUsersHandler implements RequestHandler<Object, List<User>> {
    UserService userService = new UserService();
    @Override
    public List<User> handleRequest(Object o, Context context) {
        return userService.getAllUsers();
    }
}
