package infrastructure.adapters.out;


import application.commons.NonEmptyString;
import application.domain.Sell;
import application.domain.User;
import application.ports.out.SellRepository;

import java.util.*;

public class InMemorySellRepository implements SellRepository {

    private final Map<NonEmptyString, Sell> database = new HashMap<>();

    @Override
    public Optional<Sell> getSellById(NonEmptyString NonEmptyString) {
        Sell Sell = database.get(NonEmptyString);
        return Optional.ofNullable(Sell);
    }

    @Override
    public void storeSell(Sell Sell) {
        database.put(Sell.getidSell(), Sell);
    }

    @Override
    public void updateSell(Sell Sell){database.replace(Sell.getidSell(), Sell);}

    @Override
    public void deleteSell(Sell Sell){database.remove(Sell.getidSell(), Sell);}

    @Override
    public Collection<Sell> listSell(int limit, int skip) {
        return database.values();
    }

    @Override
    public Integer countSell() {
        return database.size();
    }
}
