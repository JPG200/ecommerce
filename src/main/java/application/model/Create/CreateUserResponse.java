package application.model.Create;

import application.commons.operation.ApplicationResponse;
import application.domain.User;

public class CreateUserResponse implements ApplicationResponse {
    private final User user;

    public CreateUserResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "user=" + user +
                '}';
    }
}
