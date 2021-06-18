package application.ports.out;

import application.commons.NonEmptyString;
import application.domain.IdentificationNumber;
import application.domain.Product;
import application.domain.ProductId;
import application.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProductByName(NonEmptyString NonEmptyString);

    void storeProduct(Product Product);

    void deleteProduct(Product Product);

    void updateProduct(Product Product);

    Collection<Product> listProduct(int limit, int skip);

    Integer countProduct();
}
