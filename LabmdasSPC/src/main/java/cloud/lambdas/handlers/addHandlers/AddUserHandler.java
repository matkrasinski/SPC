package cloud.lambdas.handlers.addHandlers;

import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddUserHandler implements RequestHandler<User, User> {
    UserService userService = new UserService();

    @Override
    public User handleRequest(User user, Context context) {
        userService.addUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getHashedPassword(), user.isAdmin());
        return userService.findUserByEmail(user.getEmail());
    }
}
