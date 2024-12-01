import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите стороны треугольника:");

            int a = getValidInput(scanner, "Введите сторону a: ");
            int b = getValidInput(scanner, "Введите сторону b: ", a);
            int c = getValidInput(scanner, "Введите сторону c: ", a, b);

            if (isValidTriangle(a, b, c)) {
                System.out.println("Треугольник. \nТип треугольника: " + getTriangleType(a, b, c));
            } else {
                System.out.println("Не треугольник: сумма двух сторон должна быть больше третьей.");
            }
        } catch (Exception e) {
            System.err.println("Критическая ошибка. Попробуйте перезапустить программу.");
            e.printStackTrace();
        }
    }

    static int getValidInput(Scanner scanner, String prompt, int... previous) {
        while (true) {
            // Показываются ранее введённые значения, если это не первый ввод
            if (previous.length > 0 && (previous[0] != 0 || previous.length > 1)) {
                System.out.print("Ранее введённые стороны: ");
                for (int value : previous) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                if (!input.matches("\\d+")) {
                    throw new NumberFormatException("Ошибка: ввод должен быть неотрицательным целым числом.");
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static boolean isValidTriangle(int a, int b, int c) {
        return a >= 0 && b >= 0 && c >= 0 &&
                (long) a + b > c &&
                (long) a + c > b &&
                (long) b + c > a;
    }

    static String getTriangleType(int a, int b, int c) {
        if (a == b && b == c) {
            return "Равносторонний";
        } else if (a == b || b == c || a == c) {
            return "Равнобедренный";
        } else {
            return "Разносторонний";
        }
    }
}