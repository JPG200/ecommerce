package application.ports.in;

import application.commons.operation.ApplicationUseCase;
import application.model.Create.CreateUserRequest;
import application.model.Create.CreateUserResponse;

public interface CreateUserUseCase extends ApplicationUseCase<CreateUserRequest, CreateUserResponse> {
}
