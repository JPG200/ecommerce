package application.ports.out;

import application.domain.Client;
import application.domain.IdentificationNumber;
import java.util.Collection;
import java.util.Optional;

public interface ClientRepository {

    Optional<Client> getClientById(IdentificationNumber IdentificationNumber);

    void storeClient(Client Client);

    void deleteClient(Client Client);

    void updateClient(Client Client);

    Collection<Client> listClient(int limit, int skip);

    Integer countClient();
}
