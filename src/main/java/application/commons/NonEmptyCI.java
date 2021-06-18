package application.commons;
import static application.commons.IntUtils.*;

public class NonEmptyCI {
    private final int value;

    public NonEmptyCI(int value) {
        Validate.notNull(value, "Inventory can not be null");
        Validate.isTrue(nonBlankCI(value),"Inventory can not be empty");
        this.value = value;
    }

    public int getValue(){return value;}


    @Override
    public String toString() {
        return "NonEmptyString{" +
                "value='" + value + '\'' +
                '}';
    }
}
