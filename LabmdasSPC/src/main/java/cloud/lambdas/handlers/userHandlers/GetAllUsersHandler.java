package cloud.lambdas.handlers.userHandlers;


import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

public class GetAllUsersHandler implements RequestHandler<Void,List<User>> {
    UserService userService = new UserService();

    @Override
    public List<User> handleRequest(Void unused, Context context) {
       return userService.getAllUsers();
    }
}
