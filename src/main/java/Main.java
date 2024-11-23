import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите стороны треугольника:");
        int a;
        int b;
        int c;

        try {
            a = getValidInput(scanner, "Введите сторону a: ");
            b = getValidInput(scanner, "Введите сторону b: ");
            c = getValidInput(scanner, "Введите сторону c: ");

            if (isValidTriangle(a, b, c)) {
                System.out.println("Треугольник. \nТип треугольника: " + getTriangleType(a, b, c));
            } else {
                System.out.println("Не треугольник");
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода. Попробуйте снова.");
        }
    }

    static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.out.println("Ошибка: ввод должен содержать только целое неотрицательное число, без символов и букв.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введенное число слишком велико. Введите число от 0 до " + Integer.MAX_VALUE + ".");
            }
        }
    }

    static boolean isValidTriangle(int a, int b, int c) {
        return a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a;
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