package application.commons;

import static application.commons.IntUtils.*;

public class NonEmptyId {
    private final int value;

    public NonEmptyId(int value){
        Validate.notNull(value, "NonEmpryInt can not be null");
        Validate.isTrue(nonBlankId(value),"NonEmptyInt can not be empty");
        this.value=value;
    }

    public int getValue(){return value;}



    @Override
    public String toString() {
        return "NonEmptyString{" +
                "value='" + value + '\'' +
                '}';
    }
}
