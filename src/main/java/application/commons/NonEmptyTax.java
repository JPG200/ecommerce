package application.commons;

import static application.commons.IntUtils.*;

public class NonEmptyTax {

    private final double value;


    public NonEmptyTax(double value) {
        Validate.notNull(value, "NonEmpryInt can not be null");
        Validate.isTrue(nonBlankTaxes(value),"NonEmptyInt can not be empty");
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
