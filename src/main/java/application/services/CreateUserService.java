package application.services;

import application.commons.NonEmptyString;
import application.domain.*;
import application.errors.InputDataError;
import application.errors.UserAlredyExist;
import application.model.Create.CreateUserRequest;
import application.model.Create.CreateUserResponse;
import application.ports.out.UserRepository;

import java.util.Optional;

public class CreateUserService {

    private final UserRepository repository;

    public CreateUserService(UserRepository repository) {
        this.repository = repository;
    }

    public CreateUserResponse execute(CreateUserRequest request){
        User User = validateInput(request);
        NonEmptyString idName = User.getUserName();
        Optional<User> UserById = repository.getUserById(idName);
        if (UserById.isPresent()) {
            throw new UserAlredyExist(idName);
        }

        repository.storeUser(User);

        return new CreateUserResponse(User);
    }
    private User validateInput(CreateUserRequest request) {
        try {
            NonEmptyString Username = new NonEmptyString(request.getUserName());
            NonEmptyString UserPass = new NonEmptyString(request.getUserPass());
            TypeUser UserType = TypeUser.valueOf(request.getUserType());

            return new User(
                    Username,
                    UserPass,
                    UserType
            );
        } catch (RuntimeException e) {
            throw new InputDataError(e.getMessage());
        }
    }
}
