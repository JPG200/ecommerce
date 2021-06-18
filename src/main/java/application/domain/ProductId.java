package application.domain;

import application.commons.*;

import java.util.Objects;

import static application.commons.IntUtils.*;

public class ProductId {

    private final int value;

    public ProductId(int value) {
        Validate.notNull(value, "Product ID can not be null");
        Validate.isTrue(nonBlankId(value),"Product ID number can not be null");
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId that = (ProductId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "ProductID{" +
                "value='" + value + '\'' +
                '}';
    }
}
