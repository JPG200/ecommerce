package application.ports.out;

import application.commons.NonEmptyString;
import application.domain.Sell;

import java.util.Collection;
import java.util.Optional;

public interface SellRepository {
    Optional<Sell> getSellById(NonEmptyString identificationNumber);

    void storeSell(Sell Sell);

    void deleteSell(Sell Sell);

    void updateSell(Sell Sell);

    Collection<Sell> listSell(int limit, int skip);

    Integer countSell();

}
