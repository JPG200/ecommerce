package application.domain;

import application.commons.StringUtils;
import application.commons.Validate;

import java.util.Objects;

public class IdentificationNumber {
    private final String value;


    public IdentificationNumber(String value) {
        Validate.notNull(value, "IdentificationNumber can not be null");
        Validate.isTrue(StringUtils.nonBlank(value), "IdentificationNumber can not be empty");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentificationNumber that = (IdentificationNumber) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "IdentificationNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
