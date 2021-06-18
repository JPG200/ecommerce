package application.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIdTest {
    @Test
    @DisplayName("Null is not a valid id number")
    void nullIsNotValidIdNumber() {
        //AAA, [Arrange, Act, Assert]
        String nullId = null;

        // act
        Executable operation = () -> new IdentificationNumber(nullId);

        // assert
        assertThrows(NullPointerException.class, operation);
    }

    @Test
    void emptyStringIsNotValidIdNumber() {
        String emptyString = "";

        Executable operation = () -> new IdentificationNumber(emptyString);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, operation);
        assertEquals(illegalArgumentException.getMessage(), "IdentificationNumber can not be empty");
    }

    @Test
    void whiteSpaceStringIsNotValidIdNumber() {
        String whiteSpaceString = " ";

        Executable operation = () -> new IdentificationNumber(whiteSpaceString);

        assertThrows(IllegalArgumentException.class, operation);
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "12345678",
            "123456789",
            "1234567890",
            "12345678901",
            "123456789012",
            "1234567890123",
            "12345678901234",
            "123456789012345",
            "1234567890123456",
            "12345678901234567",
            "123456789012345678",
            "1234567890123456789",
            "12345678901234567890",
    })
    void _validDigitsStringRangeIsValidIdNumber(String idNumberString) {
        Executable operation = () -> new IdentificationNumber(idNumberString);
        assertDoesNotThrow(operation);
    }


    @Test
    void _1DigitsStringIsValidIdNumber() {
        String idNumberStr = "1";
        Executable operation = () -> new IdentificationNumber(idNumberStr);

        assertThrows(IllegalArgumentException.class, operation);
    }
}
