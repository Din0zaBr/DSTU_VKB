/**
 * Автор: Данил Ковалев ВКБ22
 */
package programmingLanguagesJava.laboratories.firstLaboratory;


import com.google.common.math.BigIntegerMath;
import org.paukov.combinatorics3.Generator;
import programmingLanguagesJava.laboratories.ConsoleReader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    /**
     * Генерация массивов, которые содержат определенное количество элементов, сгенерированных с использованием класса Random
     * ints - генерация случайных целых чисел (20 элементов) в определенном диапазоне (от -100 до 21)
     * toArray используется для преобразования сгенерированных значений в массив целых чисел
     * static final - создание статических констант. Нужны для того, чтобы не пересоздавать массивы при каждом вызове функции
     */
    static final int[] firstIntStream = new Random().ints(20, -100, 21).toArray();
    static final int[] secondIntStream = new Random().ints(15, -100, 16).toArray();
    static final int[] thirdIntStream = new Random().ints(10, -100, 11).toArray();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер задания: ");
        var question = scanner.nextInt();

        // %d заменяется на значение переменной question
        System.out.printf("---------------------------------------------------\nРезультат %d задания:\n", question);

        Object result = switch (question) {
            /*
               1. Метод executeTask из класса ConsoleReader принимает класс решения, номер вопроса и аргументы
               1.0 метод executeTask принимает аргумент, который является ссылкой на класс
               p.s. Это означает, что внутри метода executeTask можно использовать объект, который относится к определенному классу
               1.1 String.valueOf - преобразование int в String.
               1.2 Нужен для того, чтобы преобразовать номер вопроса в строку, так как метод executeTask принимает String.
               2. Динамически формирует имя метода на основе номера вопроса (например, "seventeenthQuestion"), преобразуя его в CamelCase
               3. Далее метод находит соответствующий метод в классе решения, используя рефлексию, и вызывает его, передавая аргументы.
               3.1 Рефлексия - механизм, позволяющий получить информацию о методах и классах во время выполнения программы.
               p. s. По сути, это возможность программы изучать и изменять свой собственный код во время выполнения программы.
               4. Если метод не может быть найден или вызван по какой-либо причине, возвращается сообщение "Вы выбрали неверное задание".
             */
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 18 ->
                    ConsoleReader.executeTask(Solution.class, String.valueOf(question), " ");

            /*
              По сути, то же самое, однако вместо " ", как в предыдущем case, теперь передаётся scanner.next()
              scanner.next() - это метод, который возвращает следующее значение из потока ввода
              yield - это ключевое слово, которое используется для возврата значения из блока switch
             */
            case 12, 13, 21 -> {
                System.out.print("Введите ваше число: ");
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.next());
            }

            /*
              По сути, то же самое, однако вместо scanner.next(), как в предыдущем case, теперь передаётся scanner.nextLine()
              scanner.nextLine() - это метод, который возвращает следующую строку из потока ввода
             */
            case 14, 15 -> {
                System.out.print("Введите строку: ");
                scanner.nextLine();
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.nextLine());
            }

            case 16, 22, 23 -> {
                System.out.print("Введите два числа через пробел: ");
                scanner.nextLine();
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.nextLine());
            }

            /*
                1. var используется для объявления переменных с неявным типом.
                2. При использовании var, компилятор самостоятельно определяет тип переменной на основе выражения,
                к которому она присваивается.
             */
            case 17 -> {
                System.out.println("Введите номер фигуры:\n1.Круг\n2.Прямоугольник\n3.Треугольник");
                var numberOfFigure = scanner.next();
                // У Scanner бывает баг, что он не считывает, поэтому нужно иногда переносить вот так
                scanner.nextLine();

                var choice = switch (numberOfFigure) {
                    case "1" -> "Введите радиус: ";
                    case "2" -> "Введите ширину и длину через пробел: ";
                    case "3" -> "Введите длину высоты и основания через пробел: ";
                    default -> throw new RuntimeException("Ошибка. Неправильно выбрали вариант фигуры. ");
                };

                System.out.print(choice);
                numberOfFigure += " " + scanner.nextLine();
                yield seventeenthQuestion(numberOfFigure);
            }

            case 19 -> {
                System.out.print("""
                        Введите что вы хотите сделать:\s
                        1. Вычислить сумму главной диагонали,\s
                        2. Вычислить сумму побочной диагонали\s
                        """);

                yield nineteenthQuestion(scanner.next());
            }

            /*
                StringBuilder() - нужен для того, чтобы строить строки по частям.
             */
            case 20 -> {
                var strBuilder = new StringBuilder();

                while (true) {
                    System.out.print("Вводите числа (для завершения введите 0): ");
                    var number = scanner.next();
                    /*
                    Метод append - добавляет строку в конец StringBuilder.
                    (" ") - разделитель, который добавляется после каждого числа.
                     */
                    strBuilder.append(number).append(" ");

                    if (number.equals("0"))
                        break;
                }
                /*
                метод toString() - возвращает содержимое StringBuilder в виде строки.
                 */
                yield twentiethQuestion(strBuilder.toString());
            }

            case 24 -> {
                var strBuilder = new StringBuilder();

                System.out.print("Введите I: ");
                strBuilder.append(scanner.next()).append(" ");

                for (int i = 0; i < 3; i++) {
                    System.out.print("Введите R" + i + 1 + ": ");
                    strBuilder.append(scanner.next()).append(" ");
                }

                yield twentyFourthQuestion(strBuilder.toString());
            }

            case 25 -> {
                System.out.print("Введите номер дня недели (от 1 до 7): ");
                yield twentyFifthQuestion(scanner.next());
            }

            default -> "Вы выбрали неверное задание";
        };

        scanner.close();
        System.out.println(result);
    }

    /**
     * 1. Вычислить z = Math.exp(Math.abs(max_x)) - Math.exp(Math.abs(max_y))) / Math.sqrt((Math.abs(max_x * max_y)))
     * где:
     * - max_x - наибольший элемент массива X(20);
     * - max_y - наибольший элемент массива Y(15).
     * Для вычисления наибольшего элемента массива использовать функцию.
     */
    @SuppressWarnings("unused")
    public static String firstQuestion(String ignoredUnused) {
        // String ignoredUnused - нужен для того, чтобы не было ошибки, если в методе не будет аргументов
        // orElseTrow возвращает значение, если оно существует, в ином случае будет возращена ошибка
        var max_x = Arrays.stream(Solution.firstIntStream)
                .max()
                .orElseThrow();
        var max_y = Arrays.stream(Solution.secondIntStream)
                .max()
                .orElseThrow();
        var result = (Math.exp(Math.abs(max_x)) - Math.exp(Math.abs(max_y))) / Math.sqrt(Math.abs(max_x * max_y));
        // String.format - форматирование строки.
        // %d - целочисленный тип данных
        // %f - дробный тип данных
        return String.format("z = ( e ^ |%d| - e ^ |%d| ) / sqrt(| %d * %d |) = %f", max_x, max_y, max_x, max_y, result);
    }

    /**
     * 2. Даны массивы действительных чисел А(20), B(15), C(10).
     * Вычислить M = (S+T+K)/2, где S, T, K – суммы положительных элементов массивов А, В, С соответственно.
     * Для вычисления суммы положительных элементов использовать функцию.
     */
    @SuppressWarnings("unused")
    public static String secondQuestion(String ignoredUnused) {
        // -> - лямбда выражение для удобства. (Вместо анонимного класса)
        var s = Arrays.stream(Solution.firstIntStream)
                .filter(n -> n > 0)
                .sum();
        var t = Arrays.stream(Solution.secondIntStream)
                .filter(n -> n > 0)
                .sum();
        var k = Arrays.stream(Solution.thirdIntStream)
                .filter(n -> n > 0)
                .sum();
        var result = (s + t + k) / 2.0;
        // String.format - форматирование строки.
        // %d - целочисленный тип данных
        // %f - дробный тип данных
        return String.format("М = (%d + %d + %d) / 2 = %f", s, t, k, result);
    }

    /**
     * 3. Даны целые числа m, n. Вычислить с = m!/(n! * (m-n)!).
     * Для вычисления факториала использовать функцию.
     */
    @SuppressWarnings("unused")
    public static String thirdQuestion(String ignoredUnused) {
        // когда мы определяем самые базовые(явные, тривиальные) типы, то нельзя писать var.
        int m = 4, n = 2;
        // BigInteger для обработки больших факториалов
        BigInteger factorialM = BigIntegerMath.factorial(m),
                factorialN = BigIntegerMath.factorial(n),
                factDiff = BigIntegerMath.factorial(m - n);
        // в Java, к сожалению, нет перегрузки операторов, поэтому тут математические действия делаются через методы
        // Взято с библиотеки

        // divide - деление
        // multiply - умножение
        var result = factorialM.divide(factorialN.multiply(factDiff));
        return String.format("c = %d! / (%d! * (%d - %d)!) = %d", m, n, m, n, result);
    }

    /**
     * 4. Даны действительные х, у, z. Составить программу вычисления значения
     * Math.sqrt(pow_x + pow_y + Math.pow(Math.sin(x * y), 2)) + Math.sqrt(pow_x + pow_z + Math.pow(Math.sin(x * z), 2))
     * + Math.sqrt(pow_z + pow_y + Math.pow(Math.sin(z * y), 2))
     */
    @SuppressWarnings("unused")
    public static String fourthQuestion(String ignoredUnused) {
        // Здесь использована библиотека, которая генерирует комбинации из (1, 2, 3) по 2 элемента.
        // map здесь делается то же самое, но есть небольшие разновидности, которые переделывают нам элементы к
        // нужному типу данных. По умолчанию generator возвращает итератор, где каждый элемент - это список с числами.
        var result = Generator.combination(1, 2, 3).simple(2).stream()
                .mapToDouble(x ->
                        // Происходит итерация по комбинациям и вычисляется значение для каждой комбинации
                        Math.sqrt(Math.pow(x.getFirst(), 2) + Math.pow(x.getLast(), 2) +
                                Math.pow(Math.sin(x.getFirst() * x.getLast()), 2)))
                .sum();
        return String.format("s = sqrt(1 + 4 + sin(2)^2) + sqrt(1 + 9 + sin(9)^2) + sqrt(4 + 9 + sin(6)^2 = %f", result);
    }

    /**
     * 5. Составить программу для вычисления среднего арифметического положительных элементов массивов Х(20), Y(15),
     * Z(10), используя в качестве подпрограммы функцию.
     */
    @SuppressWarnings("unused")
    public static String fifthQuestion(String ignoredUnused) {
        // var не дает прописать, надо явно указать
        // int [][] - массив двумерный
        int[][] arrays = {firstIntStream, secondIntStream, thirdIntStream};
        // Нет аналога enumerate (перебор элементов), поэтому костыляка:
        try {

            return "\n" + Arrays.stream(arrays)
                    .map(array ->
                            "Среднее значение массива " +
                                    Arrays.toString(array) + ": " +
                                    String.format("%.3f", Arrays.stream(array)
                                    // форматирует десятичное число так, чтобы у него было 3 десятичных знака.
                                            .filter(x -> x > 0)
                                            .average()
                                            .orElseThrow()))

                    .collect(Collectors.joining("\n"));

        } catch (NoSuchElementException e) {
            return "Перезапустите задание, в случайном массиве только отрицательные элементы";
        }

    }

    /**
     * 6. Даны массивы А(15), Y(15), C(12).
     * Вычислить l = min(b_i) + min(c_i) if abs(min(a_i)) > 10 else 1 + min(abs(c_i))
     */
    @SuppressWarnings("unused")
    public static String sixthQuestion(String ignoredUnused) {

        var min_a = Math.abs(Arrays.stream(firstIntStream).min().orElseThrow());

        if (min_a > 10) {
            var min_b = Arrays.stream(secondIntStream)
                    .min()
                    .orElseThrow();
            var min_c = Arrays.stream(thirdIntStream)
                    .min()
                    .orElseThrow();
            return String.format("min_a = %d -> %d + %d = %d", min_a, min_b, min_c, min_b + min_c);
        }
        // Название класса::метод
        var min_abc_c = Arrays.stream(thirdIntStream)
                .map(Math::abs)
                .min()
                .orElseThrow();
        return String.format("min_a = %d -> 1 + %d = %d", min_a, min_abc_c, 1 + min_abc_c);
    }

    /**
     * 7. Дан массив D(40) вещественных чисел. Найти среднее геометрическое его элементов,
     * которые удовлетворяют условию 0 < di < 12. Для вычислений использовать функцию.
     */
    @SuppressWarnings("unused")
    public static String seventhQuestion(String ignoredUnused) {
        var D = new Random().doubles(40).toArray();

        var result = Math.pow(
                // reduce (identity, accumulator)
                // identity - начальное значение. Если 1 - произведение, если 0 - сумма
                // accumulator - применяет функцию к элементам массива


                // a - предыдущее промежуточное значение (результат умножения всех предыдущих элементов)
                // b - текущий элемент из потока данных
                // То есть
                // вход
                // 1 * 2 = x1
                // x1 * 3 = x2
                // ...
                // выход
                // число - x12
                Arrays.stream(D)
                        .filter(x -> x > 0 && x < 12)
                        .reduce(1, (a, b) -> a * b),
                // умножаем все элементы и затем берём корень степени, равной обратному значению количества элементов -> получаем среднее геометрическое
                1.0 / D.length
        );

        return String.format("Массив: %s\nСреднее геометрическое - %.3f", Arrays.toString(D), result);
    }

    /**
     * 8. Дан массив А(80) целых чисел.
     * Найти сумму и количество тех элементов массива, которые отрицательны и нечетны.
     * Использовать в качестве подпрограммы процедуру.
     */
    @SuppressWarnings("unused")
    public static String eighthQuestion(String ignoredUnused) {
        var A = new Random().ints(80).toArray();

        var result = Arrays.stream(A)
                .filter(n -> n < 0 && Math.abs(n) % 2 == 1)
                .sum();
        return String.format("Массив: %s\nСумма: %d", Arrays.toString(A), result);
    }

    /**
     * 9. Функция, вычисляющая среднее арифметическое элементов массива.
     * Написать функцию, которая вычисляет среднее арифметическое элементов массива,
     * переданного ей в качестве аргумента
     */
    @SuppressWarnings("unused")
    public static String ninthQuestion(String ignoredUnused) {
        try {

            var result = Arrays.stream(Solution.firstIntStream)
                    .average()
                    .orElseThrow();
            return String.format("Массив: %s\nСреднее арифметическое - %f", Arrays.toString(Solution.firstIntStream), result);

        } catch (NoSuchElementException e) {
            return "Перезапустите метод, только отрицательные элементы";
        }
    }

    /**
     * 10. Дан одномерный массив, состоящий из натуральных чисел.
     * Выполнить сортировку данного массива по возрастанию суммы цифр чисел.
     * Например, дан массив чисел [14, 30, 103]. После сортировки: [30, 103, 14],
     * так как сумма цифр числа 30 составляет 3 + 0 = 3, числа 103 равна 1 + 0 + 3 = 4, числа 14 равна 5.
     */
    @SuppressWarnings("unused")
    public static String tenthQuestion(String ignoredUnused) {
        var array = new Random().ints(50, 1, 10000).toArray();
        var result = Arrays.stream(array)
                // boxed - метод, который преобразует IntStream в Stream объектов типа Integer, так как
                // метод sorted требует Stream объектов, а не IntStream.
                .boxed()
                .sorted(Comparator.comparingInt(n -> {

                    int sumOfDigits = 0;
                    n = Math.abs(n);

                    while (n > 0) {
                        sumOfDigits += n % 10;
                        n /= 10;
                    }
                    return sumOfDigits;
                }))
                .toArray();

        return String.format("Массив: %s\nСортированный массив: %s", Arrays.toString(array), Arrays.toString(result));
    }

    /**
     * 11. Вывести на экран исходный массив, отсортированный массив,
     * а также для контроля сумму цифр каждого числа отсортированного массива.
     */
    @SuppressWarnings("unused")
    public static String eleventhQuestion(String ignoredUnused) {
        var array = new Random().ints(50, 1, 10000).toArray();
        var res = "Исходный массив: " + Arrays.toString(array) + "\n";
        res += "Отсортированный массив: " + Arrays.stream(array)
                .boxed()
                .sorted()
                .toList() + "\n";
        res += "Сумма цифр каждого числа: " + Arrays.stream(array)
                .map(n -> {

                    int sumOfDigits = 0;
                    n = Math.abs(n);

                    while (n > 0) {
                        sumOfDigits += n % 10;
                        n /= 10;
                    }
                    return sumOfDigits;
                })
                .boxed()
                .toList();

        return res;
    }

    /**
     * 12. Определить количество разрядов числа.
     * Написать функцию, которая определяет количество разрядов введенного целого числа.
     */
    @SuppressWarnings("unused")
    public static String twelfthQuestion(String number) {
        // parseInt - преобразование строки в число
        int n = Math.abs(Integer.parseInt(number));
        // копия числа, так как n меняется
        var p = n;

        int count = 0;

        while (n > 0) {
            count += 1;
            n /= 10;
        }

        return String.format("Количество разрядов числа: %d равно: %d", p, count);
    }

    /**
     * 13. Сумма ряда с факториалом. Вычислить сумму ряда
     */
    @SuppressWarnings("unused")
    public static String thirteenthQuestion(String number) {

        int x = Integer.parseInt(number), result = 0;

        for (int i = 1; i < 6; i++) {
            // intValue - преобразование BigInteger в int
            result += (-1) * i * (x / BigIntegerMath.factorial(i).intValue());
        }

        return String.format("sum(-1) * i * (x / i!) for i in range(1, 6) = %d", result);
    }

    /**
     * 14. Изменить порядок слов в строке на обратный.
     * Вводится строка, состоящая из слов, разделенных пробелами.
     * Следует заменить ее на строку, в которой слова идут в обратном порядке по сравнению с
     * исходной строкой. Вывести измененную строку на экран.
     */
    @SuppressWarnings("unused")
    public static String fourteenthQuestion(String words) {
        return String.join(" ", new ArrayList<>(List.of(words.split(" ")).reversed()));
    }

    /**
     * 15. Функция бинарного поиска в массиве
     * Пользователь вводит число. Сообщить, есть ли оно в массиве,
     * элементы которого расположены по возрастанию значений, а также, если есть,
     * в каком месте находится.
     * При решении задачи использовать бинарный (двоичный) поиск, который оформить в виде отдельной
     * функции.
     */
    @SuppressWarnings("unused")
    public static String fifteenthQuestion(String valueForSearch) {
        var sortedRandArr = new Random().ints(10, 0, 11).sorted().toArray();

        var value = Integer.parseInt(valueForSearch);

        // В Java есть встроенный бинарный поиск =)
        /*
        Бинарный поиск — это эффективный алгоритм поиска, который используется для нахождения элемента в !отсортированном! массиве.
        Алгоритм работает путем сравнения искомого значения с элементом в середине массива.
        Если искомое значение равно элементу в середине, то поиск завершен.
        Если искомое значение меньше элемента в середине, то поиск продолжается в левой половине массива.
        Если искомое значение больше элемента в середине, то поиск продолжается в правой половине массива.
        Этот процесс повторяется для каждой из половин, пока не будет найден искомый элемент
        или пока не останется элементов для проверки.
         */
        int index = Arrays.binarySearch(sortedRandArr, value);


        return String.format(
                "15 задание: \n" + "Массив: " + Arrays.toString(sortedRandArr) + "\nЭлемент %d %s", value,
                index >= 0 ? " на позиции " + index : " не найден в массиве"
        );
    }

    /**
     * 16. Вычисление наибольших общих делителей.
     * Найти наибольшие общие делители (НОД) для множества пар чисел.
     */
    @SuppressWarnings("unused")
    public static String sixteenthQuestion(String stringWithTwoNumbers) {
        // Здесь будет использоваться алгоритм Штейна для нахождения НОД. Его сложность O(n^2/log(n)^2)
        // По сложности кажется, что он хуже Евклида O(log(min(a, b))), но он быстрее его за счет битовых сдвигов

        var res = stringWithTwoNumbers.split(" ");
        int a = Integer.parseInt(res[0]), b = Integer.parseInt(res[1]);

        return String.format(
                "НОД чисел %s равен: %d", stringWithTwoNumbers, HelpMethods.algorithm_stein(a, b)
        );
    }


    /**
     * 17. Найти площади разных фигур. В зависимости от выбора пользователя вычислить площадь круга, прямоугольника или
     * треугольника. Для вычисления площади каждой фигуры должна быть написана отдельная функция.
     */
    @SuppressWarnings("unused")
    public static String seventeenthQuestion(String parameters) {
        var splitParameters = parameters.split(" ");

        try {

            return switch (Integer.parseInt(splitParameters[0])) {
                case 1 -> Circle.square(Double.parseDouble(splitParameters[1]));
                case 2 -> Rect.square(Integer.parseInt(splitParameters[1]), Integer.parseInt(splitParameters[2]));
                case 3 -> Triangle.square(Integer.parseInt(splitParameters[1]), Integer.parseInt(splitParameters[2]));
                default -> "Выбрали неверное задание";
            };

        } catch (IndexOutOfBoundsException e) {
            return "Вы ввели неверные данные";
        }

    }

    /**
     * 18. Найти массив с максимальной суммой элементов.
     * Сгенерировать десять массивов из случайных чисел.
     * Вывести их и сумму их элементов на экран.
     * Найти среди них один с максимальной суммой элементов.
     * Указать какой он по счету, повторно вывести этот массив и сумму его элементов.
     * Заполнение массива и подсчет суммы его элементов оформить в виде отдельной функции.
     */
    @SuppressWarnings("unused")
    public static String eighteenthQuestion(String ignoredUnused) {
        var matrix = HelpMethods.generateRandomMatrix(5, 5);

        // Наш массив в строчном виде
        var matrixString = HelpMethods.ToString(matrix);

        // Наш список с суммами.
        var sums = Arrays.stream(matrix)
                .mapToInt(row -> Arrays.stream(row)
                        .sum())
                .boxed()
                .toList();

        // Метод indexOf есть только в списках, что странно, так как в массиве он также нужен
        var index = sums.indexOf(sums.stream()
                .max(Integer::compare)
                .orElseThrow());

        return String.format(
                "Наша матрица:\n%s\nМассив с суммами:\n%s\nСтрока, где максимальная сумма %s, с индексом %d, сумма - %d",
                matrixString,
                sums,
                Arrays.toString(matrix[index]),
                index,
                Arrays.stream(matrix[index]).sum());
    }

    /**
     * 19. Вычислить сумму элементов главной или побочной диагонали матрицы.
     * Дана квадратная матрица. Вычислить сумму элементов главной или побочной диагонали в зависимости от
     * выбора пользователя. Сумма элементов любой диагонали должна вычисляться в одной и той же функции.
     */
    @SuppressWarnings("unused")
    public static String nineteenthQuestion(String parameter) {

        var matrix = HelpMethods.generateRandomMatrix(6, 6);

        var userChoice = Integer.parseInt(parameter);

        var result = Arrays.stream(matrix)
                // Напоминание: Условие ? Код при True : Код при False
                .mapToInt(row ->
                        row[userChoice == 1 ? // Если пользователь выбрал 1, берется элемент по индексу строки
                                Arrays.asList(matrix).indexOf(row) :
                                // Иначе выбор элемента с противоположной стороны диагонали
                                matrix.length - 1 - Arrays.asList(matrix).indexOf(row)])
                .sum();

        return String.format("Наша матрица: %s\nСумма диагонали - %d", HelpMethods.ToString(matrix), result);
    }

    /**
     * 20. Функция перевода десятичного числа в двоичное.
     * Переводить в двоичную систему счисления вводимые в десятичной системе счисления числа до тех пор,
     * пока не будет введен 0. Для перевода десятичного числа в двоичное написать функцию.
     */
    @SuppressWarnings("unused")
    public static String twentiethQuestion(String numbers) {
        var strBuilder = new StringBuilder();
        var splitNumbers = Arrays.stream(numbers.split(" "))
                // Разделение входной строки на числа, преобразование их в целочисленные значения и
                .mapToInt(Integer::parseInt)
                .iterator();
        // создание итератора для обхода этих чисел.

        while (true) {
            var number = splitNumbers.next();

            if (number == 0)
                break;

            // Встроенная функция для перевода в различные системы исчисления.
            // Может переводить в СС от 2 до 36 включительно.
            var binaryNumber = Integer.toString(number, 2);
            strBuilder.append(String.format("Результат перевода двоичного числа %d - %s\n", number, binaryNumber));
        }

        return strBuilder.toString();
    }

    /**
     * 21. Вычислить значения функции y=f(x) на заданном диапазоне.
     * Вычислить значения нижеприведенной функции в диапазоне значений x от -10 до 10
     * включительно с шагом, равным 1.
     * y = x2 при -5 <= x <= 5;
     * y = 2*|x|-1 при x < -5;
     * y = 2x при x > 5.
     * Вычисление значения функции оформить в виде программной функции, которая принимает значение x,
     * а возвращает полученное значение функции (y).
     */
    @SuppressWarnings("unused")
    public static String twentyFirstQuestion(String xString) {
        int x = Integer.parseInt(xString), y;

        if (x <= 5)
            y = x >= -5 ? (int) Math.pow(x, 2) : 2 * Math.abs(x) - 1;
        else
            y = 2 * x;

        return String.format("y = %d", y);
    }

    /**
     * 22. Функция заполнения массива случайными числами.
     * Написать функцию, которая заполняет массив случайными числами в диапазоне, указанном пользователем.
     * Функция должна принимать два аргумента - начало диапазона и его конец, при этом ничего не возвращать.
     * Вывод значений элементов массива должен происходить в основной ветке программы.
     */
    @SuppressWarnings("unused")
    public static String twentySecondQuestion(String numbers) {

        var splitNumbers = Arrays.stream(numbers.split(" "))
                .map(Integer::parseInt)
                .iterator();

        int start = splitNumbers.next(), end = splitNumbers.next();

        var randomArray = new Random().ints(10, start, end).toArray();

        return String.format("Массив длиной 10 с случайными числами: %s", Arrays.toString(randomArray));
    }

    /**
     * 23. Написать функцию вычисления величины силы тока на участке электрической
     * цепи сопротивлением R Ом при напряжении U В.
     */
    @SuppressWarnings("unused")
    public static String twentyThirdQuestion(String numbers) {

        var splitNumbers = Arrays.stream(numbers.split(" "))
                .map(Double::parseDouble)
                // позволяет перебирать элементы в коллекции один за другим
                .iterator();

        double U = splitNumbers.next(), R = splitNumbers.next();

        return String.format("Результат 23 задания: %f", U / R);
    }

    /**
     * 24. Написать функцию вычисления напряжения на каждом из последовательно
     * соединенных участков электрической цепи сопротивлением R1, R2, R3 Ом, если сила
     * тока при напряжении U В составляет I А.
     */
    @SuppressWarnings("unused")
    public static String twentyFourthQuestion(String numbers) {

        var splitNumbers = Arrays.stream(numbers.split(" "))
                .map(Double::parseDouble)
                .iterator();

        double[] R = new double[3], U = new double[3];

        double I = splitNumbers.next();

        for (int i = 0; i < 3; i++) {
            R[i] = splitNumbers.next();
            U[i] = I * R[i];
        }

        // Создаем строки с соответствующими напряжениями. Через Decimal есть только округление, которое нужно
        // Очень странно, что разработчики не сделали в том же самом Math для этого нужный статический метод.
        var UString = Arrays.stream(U)
                // ToObj - преобразование DoubleStream в Stream объектов типа Double
                // Округляем до 2 знаков после запятой
                .mapToObj(number -> BigDecimal.valueOf(number)
                        .setScale(2, RoundingMode.HALF_UP)
                        .toString())
                .collect(Collectors.joining(" "));

        return String.format("Напряжение на участках 1, 2, 3 соответственно равны: %s", UString);
    }

    /**
     * 25. Составить программу для ввода на экран номера дня недели и вывода соответствующего
     * ему дня недели на русском языке.
     */
    @SuppressWarnings("unused")
    public static String twentyFifthQuestion(String number) {

        int dayNumber = Integer.parseInt(number);

        // Проверка, чтобы номер дня недели был в пределах от 1 до 7
        if (dayNumber >= 1 && dayNumber <= 7) {
            DayOfWeek dayOfWeek = DayOfWeek.of(dayNumber);
            String result = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru"));
            return String.format("Результат 25 задания: День недели: %s", result);
        }
        return "Результат 25 задания: Некорректный номер дня недели.";
    }
}


