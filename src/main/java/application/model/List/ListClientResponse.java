package application.model.List;

import application.commons.operation.ApplicationResponse;
import application.domain.Client;
import application.domain.Product;

import java.util.Collection;

public class ListClientResponse implements ApplicationResponse {
    private final Collection<Client> items;
    private final Integer total;


    public ListClientResponse(Collection<Client> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public Collection<Client> getItems(){return items;}

    public Integer getTotal(){return total;}
}
