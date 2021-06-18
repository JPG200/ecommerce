package application.services;
import application.domain.User;
import application.model.List.ListUserRequest;
import application.model.List.ListUserResponse;
import application.ports.in.ListUserUseCase;
import application.ports.out.UserRepository;

import java.util.Collection;

public class ListUserService implements ListUserUseCase {
    private final UserRepository repository;

    public ListUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListUserResponse execute(ListUserRequest request) {
        Collection<User> User = repository.listUser(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = repository.countUser();
        return new ListUserResponse(User, total);
    }
}
