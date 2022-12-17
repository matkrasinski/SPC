package cloud.lambdas.handlers;

import cloud.lambdas.pojo.User;
import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.List;

public class UserHandler {

    UserService userService = new UserService();

    public Boolean handleAddUserRequest(User user, Context context) {
        return userService.addUser(user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getHashedPassword(), user.isAdmin());

    }
    public Boolean handleDeleteUserRequest(Long userId, Context context) {
        return userService.deleteUser(userId);
    }

    public List<User> handleGetAllUsersRequest(Void unused, Context context) {
        return userService.getAllUsers();
    }

    public User handleFindUserByIdRequest(Long id, Context context) {
        return userService.findUserById(id);
    }

    public User handleFindUserByEmail(String s, Context context) {
        return userService.findUserByEmail(s);
    }


}
