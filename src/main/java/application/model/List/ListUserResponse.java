package application.model.List;

import application.commons.operation.ApplicationResponse;
import application.domain.Sell;
import application.domain.User;

import java.util.Collection;

public class ListUserResponse implements ApplicationResponse {
    private final Collection<User> items;
    private final Integer total;


    public ListUserResponse(Collection<User> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public Collection<User> getItems(){return items;}

    public Integer getTotal(){return total;}
}
