package infrastructure.adapters.out;

import application.commons.NonEmptyString;
import application.domain.Client;
import application.domain.User;
import application.ports.out.UserRepository;
import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private final Map<NonEmptyString, User> database = new HashMap<>();

    @Override
    public Optional<User> getUserById(NonEmptyString NonEmptyString) {
        User User = database.get(NonEmptyString);
        return Optional.ofNullable(User);
    }

    @Override
    public void storeUser(User User) {
        database.put(User.getUserName(), User);
    }

    @Override
    public void updateUser(User User){database.replace(User.getUserName(),User);}

    @Override
    public void deleteUser(User User){database.remove(User.getUserName(), User);}

    @Override
    public Collection<User> listUser(int limit, int skip) {
        return database.values();
    }

    @Override
    public Integer countUser() {
        return database.size();
    }
}
