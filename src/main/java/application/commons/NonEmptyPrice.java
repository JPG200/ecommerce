package application.commons;

import static application.commons.IntUtils.*;

public class NonEmptyPrice {
private final double  value;


    public NonEmptyPrice(double value) {
        Validate.notNull(value, "NonEmpryInt can not be null");
        Validate.isTrue(nonBlankBP(value),"NonEmptyInt can not be empty");
        this.value=value;
    }

    public double getValue(){return value;}


    @Override
    public String toString() {
        return "NonEmptyString{" +
                "value='" + value + '\'' +
                '}';
    }
}
