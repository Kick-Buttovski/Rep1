import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Читаем введенное пользователем выражение
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();
        String answer = calc(expression);
        System.out.println(answer);
    }

    public static String calc(String expression) {
        // Разделяем выражение на три части составного массива
        String[] units = expression.split(" ");

        // Проверяем выражение на соответствие формату - два операнда и один оператор (+, -, /, *)
        if (units.length != 3) {
            return "Ошибка: формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)";
        }

        try {
            // Исключение try catch, для того чтобы пользователь понимал какие цифры допустимы
            int a, b, answer;
            String operator = units[1];

            // В зависимости от цифр выражения присваиваем элементам числовые значения массива
            if (isRomanExpression(expression)) {
                a = getRomanValue(units[0]);
                b = getRomanValue(units[2]);
            } else {
                a = Integer.parseInt(units[0]);
                b = Integer.parseInt(units[2]);
            }

            // Проверяем, что числа в диапазоне от 1 до 10
            if (a < 1 || a > 10 || b < 1 || b > 10) {
                return "Ошибка: допустимы числа от 1 до 10 включительно";
            }

            // Проводим арифметическую операцию в соответсвии с введенным оператором
            switch (operator) {
                case "+":
                    answer = a + b;
                    break;
                case "-":
                    answer = a - b;
                    break;
                case "*":
                    answer = a * b;
                    break;
                case "/":
                    answer = a / b;
                    break;
                default:
                    return "Ошибка: неправильный оператор";
            }

            // Проверяем римское ли выражение
            if (isRomanExpression(expression)) {
                // Если ответ меньше нуля, то выдаем ошибку, иначе переводим ответ в римский формат
                if (answer < 1) {
                    return "Ошибка: В римской системе нет нуля и отрицательных чисел";
                } else {
                    return toRomanNumber(Integer.toString(answer));
                }
            } else {
                return Integer.toString(answer); // Возвращаем ответ арабского выражения в виде строки
            }

        } catch (NumberFormatException e) {
            return "Ошибка: допустимы целые числа от 1 до 10 включительно, либо римские от I до X включительно";
        }
    }

    // Проверяем, что число римское, т.е. находится в перечислении RomanNumber
    private static boolean isRomanNumber(String number) {
        try {
            RomanNumber.valueOf(number);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Проверяем, что выражение содержит только римские числа, используя функцию isRomanNumber
    private static boolean isRomanExpression(String expression) {
        String[] units = expression.split(" ");
        return isRomanNumber(units[0]) && isRomanNumber(units[2]);
    }

    // Получение числового значения римского числа
    private static int getRomanValue(String number) {
        return RomanNumber.valueOf(number).getValue();
    }

    // Преобразование числа в римскую запись
    private static String toRomanNumber(String number) {
        return RomanNumber.getTranslationByValue(Integer.parseInt(number));
    }
}