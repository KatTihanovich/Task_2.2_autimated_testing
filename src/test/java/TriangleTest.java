import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1, true",
            "1, 2, 2, true",
            "2, 3, 4, true",
            "1000000, 1050000, 120001, true",
            "8, 4, 9, true",
            "28, 59, 12, false",
            "27, 68, 0, false",
            "6415653, 32, 1, false",
            "0, 0, 0, false",
            "2147483647, 2147483646, 2147483645, true",
            "2147483647, 2147483647, 2147483646, true",
            "2147483647, 2147483647, 2147483647, true"
    })
    void testIsValidTriangle(int a, int b, int c, boolean expected) {
        int[] sides = {a, b, c};
        assertEquals(expected, Main.isValidTriangle(sides));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 1, Равносторонний",
            "1, 2, 2, Равнобедренный",
            "2, 3, 4, Разносторонний",
            "1000000, 1050000, 120001, Разносторонний",
            "8, 4, 9, Разносторонний",
            "2147483647, 2147483646, 2147483645, Разносторонний",
            "2147483647, 2147483647, 2147483646, Равнобедренный",
            "2147483647, 2147483647, 2147483647, Равносторонний"
    })
    void testGetTriangleType(int a, int b, int c, String expectedType) {
        int[] sides = {a, b, c};
        assertEquals(expectedType, Main.getTriangleType(sides));
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5",
            "0, 0",
            "12345, 12345"
    })
    void testValidateAndParseInput_Valid(String input, int expected) {
        assertEquals(expected, Main.validateAndParseInput(input));
    }

    @ParameterizedTest
    @CsvSource({
            "'-1', 'java.lang.NumberFormatException'",
            "'-', 'java.lang.NumberFormatException'",
            "'5.1', 'java.lang.NumberFormatException'",
            "'15ю', 'java.lang.NumberFormatException'",
            "'ф', 'java.lang.NumberFormatException'",
            "'№', 'java.lang.NumberFormatException'"
    })
    void testValidateAndParseInput_Invalid(String input, Class<? extends Throwable> exceptionClass) {
        assertThrows(exceptionClass, () -> Main.validateAndParseInput(input));
    }
}