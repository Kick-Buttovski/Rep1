import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // Читаем введенное пользователем выражение
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();
        String answer = calc(expression);
        System.out.println(answer);
    }

    public static String calc(String expression) throws Exception {
        // Разделяем выражение на три части составного массива
        String[] units = expression.split(" ");

        // Проверяем выражение на соответствие формату
        if (units.length <= 2) {
            throw new Exception("//т.к. строка не является математической операцией");
        } else if (units.length != 3) {
            throw new Exception("//формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        int a, b, answer;
        String operator = units[1];

        // В зависимости от цифр выражения присваиваем элементам числовые значения массива
        if (isRomanNumber(units[0]) && isRomanNumber(units[2])) {
            a = getRomanValue(units[0]);
            b = getRomanValue(units[2]);
        } else if (isDigit(units[0]) & isDigit(units[2])) {
            a = Integer.parseInt(units[0]);
            b = Integer.parseInt(units[2]);
        } else if (isRomanNumber(units[0]) & isDigit(units[2]) || isDigit(units[0]) & isRomanNumber(units[2])) {
            throw new Exception("//т.к. используются одновременно разные системы счисления");
        } else {
            throw new Exception("//неправильный ввод чисел");
        }

        // Проверка что цифры от 1 до 10 включительно
        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new Exception("//для ввода допустимы римские или арабские числа от 1 до 10 включительно");
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
                throw new Exception("//неправильный оператор");
        }

        // Выводим ответ в формате системы счисления введенного выражения
        if (isRomanNumber(units[0]) && isRomanNumber(units[2])) {
            // Если в римском выражении ответ меньше нуля, то выводим ошибку
            if (answer < 1) {
                throw new Exception("//т.к. в римской системе нет отрицательных чисел");
            } else {
                return toRomanNumber(Integer.toString(answer)); // Возвращаем ответ римского выражения
            }
        } else {
            return Integer.toString(answer); // Возвращаем ответ арабского выражения
        }
    }

    // Проверяем, что число целое арабское
    private static boolean isDigit(String x) throws NumberFormatException {
        try {
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
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

    // Получение числового значения римского числа
    private static int getRomanValue(String number) {
        return RomanNumber.valueOf(number).getValue();
    }

    // Преобразование числа в римскую запись
    private static String toRomanNumber(String number) {
        return RomanNumber.getTranslationByValue(Integer.parseInt(number));
    }
}

// end