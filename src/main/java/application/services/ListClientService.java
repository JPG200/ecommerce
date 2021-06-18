package application.services;

import application.domain.Client;
import application.model.List.ListClientRequest;
import application.model.List.ListClientResponse;
import application.ports.in.ListClientUseCase;
import application.ports.out.ClientRepository;

import java.util.Collection;

public class ListClientService implements ListClientUseCase {
    private final ClientRepository repository;

    public ListClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ListClientResponse execute(ListClientRequest request) {
        Collection<Client> Client = repository.listClient(
                request.getLimit(),
                request.getSkip()
        );
        Integer total = repository.countClient();
        return new ListClientResponse(Client, total);
    }
}
