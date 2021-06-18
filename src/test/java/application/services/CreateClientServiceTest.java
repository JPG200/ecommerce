package application.services;

import application.commons.NonEmptyString;
import application.domain.Client;
import application.domain.IdentificationNumber;
import application.domain.IdentificationType;
import application.errors.ClientAlredyExist;
import application.model.Create.CreateClientRequest;
import application.model.Create.CreateClientResponse;
import application.ports.out.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateClientServiceTest {

    @Test
    void ifClientDoesNotExistsItGetsCreated() {
        // arrange
        ClientRepository repository = Mockito.mock(ClientRepository.class);
        Mockito.when(repository.getClientById(ArgumentMatchers.any(IdentificationNumber.class)))
                .thenReturn(Optional.empty());


        CreateClientService createClientService = new CreateClientService(repository);
        final String idNumber = "12345678";
        CreateClientRequest request = new CreateClientRequest(
                "CLIENT_NAME",
                "CLIENT_LASTNAME",
                "CLIENT_UBICATION",
                idNumber,
                "CC"
        );

        // act
        CreateClientResponse response = createClientService.execute(request);

        // assert
        assertAll(
                () -> assertDoesNotThrow(() -> createClientService.execute(request)),
                () -> assertEquals(
                        response.getClient().getidentificationNumber().getValue(),
                        idNumber
                ),
                () -> Mockito.verify(repository, Mockito.times(2))
                        .getClientById(ArgumentMatchers.any(IdentificationNumber.class))
        );
    }


    @Test
    void ifClientExistsItThrowsAnException() {
        Client Client = new Client(
                new NonEmptyString("CLIENT_NAME"),
                new NonEmptyString("CLIENT_LASTNAME"),
                new NonEmptyString("CLIENT_UBICATION"),
                new IdentificationNumber("12345678"),
                IdentificationType.CC
        );

        ClientRepository repository = Mockito.mock(ClientRepository.class);
        Mockito.when(repository.getClientById(ArgumentMatchers.any(IdentificationNumber.class)))
                .thenReturn(Optional.of(Client));

        CreateClientService createClientService = new CreateClientService(repository);
        CreateClientRequest request = new CreateClientRequest(
                Client.getclientName().getValue(),
                Client.getclientLastName().getValue(),
                Client.getclientUbication().getValue(),
                Client.getidentificationNumber().getValue(),
                Client.getIdentificationType().name()
        );

        assertAll(
                () -> assertThrows(ClientAlredyExist.class, () -> createClientService.execute(request)),
                () -> Mockito.verify(repository, Mockito.times(0))
                        .storeClient(ArgumentMatchers.any(Client.class))
        );

    }
}
