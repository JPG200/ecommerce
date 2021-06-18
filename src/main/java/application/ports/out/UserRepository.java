package application.ports.out;

import application.commons.NonEmptyString;
import application.domain.User;
import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Optional<User> getUserById(NonEmptyString NonEmptyString);

    void storeUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    Collection<User> listUser(int limit, int skip);

    Integer countUser();
}
