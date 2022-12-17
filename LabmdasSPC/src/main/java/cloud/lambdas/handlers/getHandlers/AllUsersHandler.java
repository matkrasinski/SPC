package cloud.lambdas.handlers.getHandlers;


import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class AllUsersHandler {
    UserService userService = new UserService();
    public List<User> handleRequest(Context context) {
        return userService.getAllUsers();
    }
}
