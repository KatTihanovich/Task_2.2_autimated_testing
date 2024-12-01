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
        assertEquals(expected, Main.isValidTriangle(a, b, c),
                "Проверка валидности треугольника с сторонами " + a + ", " + b + ", " + c);
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
    void testGetTriangleType(int a, int b, int c, String expected) {
        assertEquals(expected, Main.getTriangleType(a, b, c),
                "Тип треугольника с сторонами " + a + ", " + b + ", " + c);
    }

    @ParameterizedTest
    @CsvSource({
            "-1.2, 5, 4",
            "5, 5.0, 4",
            "213535, -2135, -1513",
            "5.1, 12, 13",
            "15, 15ю, 25",
            "2.2, 2.2, 2.2",
            "ф, в, 2",
            "цв, 2в, №"
    })
    void testInvalidInput(String a, String b, String c) {
        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt(a);
            Integer.parseInt(b);
            Integer.parseInt(c);
        });
    }
}