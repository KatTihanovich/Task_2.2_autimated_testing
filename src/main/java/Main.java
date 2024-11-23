import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        setupLogger();

        Scanner scanner = new Scanner(System.in);
        logger.info("Программа начата.");

        System.out.println("Введите стороны треугольника:");
        int a;
        int b;
        int c;

        try {
            a = getValidInput(scanner, "Введите сторону a: ");
            b = getValidInput(scanner, "Введите сторону b: ");
            c = getValidInput(scanner, "Введите сторону c: ");

            if (isValidTriangle(a, b, c)) {
                String triangleType = getTriangleType(a, b, c);
                System.out.println("Треугольник. \nТип треугольника: " + triangleType);
                logger.info("Треугольник определен. Тип: " + triangleType);
            } else {
                System.out.println("Не треугольник");
                logger.warning("Попытка определить несуществующий треугольник: a=" + a + ", b=" + b + ", c=" + c);
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода. Попробуйте снова.");
            logger.severe("Ошибка ввода: " + e.getMessage());
        }

        logger.info("Программа завершена.\n");
    }

    static void setupLogger() {
        try {
            // Установка обработчика для записи в файл
            FileHandler fileHandler = new FileHandler("triangle.log", true);
            // Устанавливаем формат логирования без даты и времени
            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getMessage() + "\n";  // Только сообщение, без даты и времени
                }
            });
            logger.addHandler(fileHandler);

            // Настройка уровня логирования
            logger.setLevel(Level.ALL);

            // Удаление консольного вывода, если он не нужен
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка при настройке логгера: " + e.getMessage());
        }
    }

    static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (!input.matches("\\d+")) {
                System.out.println("Ошибка: ввод должен содержать только целое неотрицательное число, без символов и букв.");
                logger.warning("Некорректный ввод: " + input);
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введенное число слишком велико. Введите число от 0 до " + Integer.MAX_VALUE + ".");
                logger.warning("Число слишком велико: " + input);
            }
        }
    }

    static boolean isValidTriangle(int a, int b, int c) {
        boolean valid = a > 0 && b > 0 && c > 0 && (long) a + b > c && (long) a + c > b && (long) b + c > a;
        logger.info("Проверка треугольника: a=" + a + ", b=" + b + ", c=" + c + " -> " + (valid ? "валидный" : "невалидный"));
        return valid;
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