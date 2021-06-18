package application.services;

import application.commons.NonEmptyString;
import application.domain.*;
import application.errors.ClientAlredyExist;
import application.model.Create.CreateClientRequest;
import application.model.Create.CreateClientResponse;
import application.model.Create.CreateUserRequest;
import application.model.Create.CreateUserResponse;
import application.ports.out.ClientRepository;
import application.ports.out.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateUserServiceTest {

    @Test
    void ifUserDoesNotExistsItGetsCreated() {
        // arrange
        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getUserById(ArgumentMatchers.any(NonEmptyString.class)))
                .thenReturn(Optional.empty());


        CreateUserService createUserService = new CreateUserService(repository);
        final String idNumber = "12345678";
        CreateUserRequest request = new CreateUserRequest(
                "USER_NAME",
                "USER_PASS",
                "Admin"
        );

        // act
        CreateUserResponse response = createUserService.execute(request);

        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createUserService.execute(request)),
                () -> assertEquals(
                        response.getUser().getUserName().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getUserById(ArgumentMatchers.any(NonEmptyString.class))
        );
    }


    @Test
    void ifUserExistsItThrowsAnException() {
        User User = new User(
                new NonEmptyString("USER_NAME"),
                new NonEmptyString("USER_PASS"),
                TypeUser.Admin
        );

        UserRepository repository = Mockito.mock(UserRepository.class);
        Mockito.when(repository.getUserById(ArgumentMatchers.any(NonEmptyString.class)))
                .thenReturn(Optional.of(User));

        CreateUserService createUserService = new CreateUserService(repository);
        CreateUserRequest request = new CreateUserRequest(
                User.getUserName().getValue(),
                User.getUserPassword().getValue(),
                User.getUserType().name()
        );

        assertAll(
                () -> assertThrows(ClientAlredyExist.class, () -> createUserService.execute(request)),
                () -> Mockito.verify(repository, Mockito.times(0))
                        .storeUser(ArgumentMatchers.any(User.class))
        );

    }
}
