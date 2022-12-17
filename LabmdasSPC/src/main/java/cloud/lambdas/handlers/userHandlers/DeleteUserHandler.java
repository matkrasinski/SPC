package cloud.lambdas.handlers.userHandlers;

import cloud.lambdas.service.UserService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteUserHandler implements RequestHandler<Long,Boolean> {
    UserService userService = new UserService();

    @Override
    public Boolean handleRequest(Long userId, Context context) {
        return userService.deleteUser(userId);
    }
}
