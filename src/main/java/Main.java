import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите стороны треугольника:");

            int[] sides = new int[3];
            boolean[] isSet = new boolean[3];

            sides[0] = getValidInput(scanner, "Введите сторону a: ", sides, isSet, 0);
            sides[1] = getValidInput(scanner, "Введите сторону b: ", sides, isSet, 1);
            sides[2] = getValidInput(scanner, "Введите сторону c: ", sides, isSet, 2);

            if (isValidTriangle(sides)) {
                System.out.println("Треугольник. \nТип треугольника: " + getTriangleType(sides));
            } else {
                System.out.println("Не треугольник: сумма двух сторон должна быть больше третьей.");
            }
        } catch (Exception e) {
            System.err.println("Критическая ошибка. Попробуйте перезапустить программу.");
            e.printStackTrace();
        }
    }

    static String getInput(Scanner scanner, String prompt, int[] sides, boolean[] isSet) {
        System.out.print("Текущие значения сторон: ");
        for (int i = 0; i < sides.length; i++) {
            if (isSet[i]) {
                System.out.print(sides[i] + " ");
            }
        }
        System.out.println();

        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    static int validateAndParseInput(String input) {
        if (!input.matches("\\d+")) {
            throw new NumberFormatException("Ошибка: ввод должен быть неотрицательным целым числом.");
        }
        return Integer.parseInt(input);
    }

    static int getValidInput(Scanner scanner, String prompt, int[] sides, boolean[] isSet, int index) {
        while (true) {
            try {
                String rawInput = getInput(scanner, prompt, sides, isSet);
                int value = validateAndParseInput(rawInput);
                isSet[index] = true;
                return value;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static boolean isValidTriangle(int[] sides) {
        int a = sides[0];
        int b = sides[1];
        int c = sides[2];
        return a > 0 && b > 0 && c > 0 &&
                (long) a + b > c &&
                (long) a + c > b &&
                (long) b + c > a;
    }

    static String getTriangleType(int[] sides) {
        int a = sides[0];
        int b = sides[1];
        int c = sides[2];
        if (a == b && b == c) {
            return "Равносторонний";
        } else if (a == b || b == c || a == c) {
            return "Равнобедренный";
        } else {
            return "Разносторонний";
        }
    }
}