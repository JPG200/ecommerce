package application.services;

import application.commons.NonEmptyString;
import application.domain.Client;
import application.domain.IdentificationNumber;
import application.domain.IdentificationType;
import application.errors.ClientAlredyExist;
import application.errors.InputDataError;
import application.model.Create.CreateClientRequest;
import application.model.Create.CreateClientResponse;
import application.ports.in.CreateClientUseCase;
import application.ports.out.ClientRepository;

import java.util.Optional;

public class CreateClientService implements CreateClientUseCase {
    private final ClientRepository repository;

    public CreateClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public CreateClientResponse execute(CreateClientRequest request){
      Client client = validateInput(request);
      IdentificationNumber idNumber = client.getidentificationNumber();
      Optional<Client> clientById = repository.getClientById(idNumber);
        if (clientById.isPresent()) {
            throw new ClientAlredyExist(idNumber);
        }

        repository.storeClient(client);

        return new CreateClientResponse(client);
    }
    private Client validateInput(CreateClientRequest request) {
        try {
            NonEmptyString name = new NonEmptyString(request.getClientName());
            NonEmptyString lastName = new NonEmptyString(request.getClientLastName());
            NonEmptyString ubication = new NonEmptyString(request.getubicationClient());
            IdentificationType identificationType = IdentificationType.valueOf(request.getidType());
            IdentificationNumber identificationNumber = new IdentificationNumber(request.getIdNumberClient());

            return new Client(
                    name,
                    lastName,
                    ubication,
                    identificationNumber,
                    identificationType

            );
        } catch (RuntimeException e) {
            throw new InputDataError(e.getMessage());
        }
    }

}
