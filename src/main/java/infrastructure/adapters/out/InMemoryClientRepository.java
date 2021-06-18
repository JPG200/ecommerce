package infrastructure.adapters.out;

import application.commons.NonEmptyString;
import application.domain.Client;
import application.domain.IdentificationNumber;
import application.domain.Product;
import application.ports.out.ClientRepository;
import java.util.*;


public class InMemoryClientRepository implements ClientRepository {
    private final Map<IdentificationNumber, Client> database = new HashMap<>();

    @Override
    public Optional<Client> getClientById(IdentificationNumber IdentificationNumber) {
        Client Client = database.get(IdentificationNumber);
        return Optional.ofNullable(Client);
    }

    @Override
    public void storeClient(Client Client) {
        database.put(Client.getidentificationNumber(), Client);
    }

    @Override
    public void updateClient(Client Client){database.replace(Client.getidentificationNumber(),Client);}

    @Override
    public void deleteClient(Client Client){database.remove(Client.getidentificationNumber(), Client);}


    @Override
    public Collection<Client> listClient(int limit, int skip) {
        return database.values();
    }

    @Override
    public Integer countClient() {
        return database.size();
    }

}
