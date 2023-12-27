/**
 * Автор: Данил Ковалев ВКБ22 Вариант -
 */
package laboratories.firstLaboratory;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

import org.paukov.combinatorics3.Generator;

import com.google.common.math.BigIntegerMath;

public class Solution {
    static final IntStream firstIntStream = new Random().ints(20, -100, 21);
    static final IntStream secondIntStream = new Random().ints(15, -100, 16);
    static final IntStream thirdIntStream = new Random().ints(10, -100, 11);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер задания, чье решения вы хотите получить");

        Object result = switch (scanner.nextInt()) {
            case 1 -> firstQuestion();
            case 2 -> secondQuestion();
            case 3 -> thirdQuestion();
            case 4 -> fourthQuestion();
            case 5 -> fifthQuestion();
            case 6 -> sixthQuestion();
            case 7 -> seventhQuestion();
            case 8 -> eighthQuestion();
            case 9 -> ninthQuestion();
            case 10 -> tenthQuestion();
            case 11 -> eleventhQuestion();
            case 12 -> twelfthQuestion();
            default -> "Вы выбрали неверное задание";
        };
        System.out.println(result);
    }

    /**
     * 1. Вычислить z = Math.exp(Math.abs(max_x)) - Math.exp(Math.abs(max_y))) / Math.sqrt((Math.abs(max_x * max_y)))
     * где - наибольший элемент массива X(20); - наибольший элемент массива Y(15).
     * Для вычисления наибольшего элемента массива использовать функцию.
     *
     * @return значение после вычисления
     */

    public static double firstQuestion() {
        // orElseTrow возвращает значение, если оно существует, в ином случае будет возмущена ошибка
        var max_x = Solution.firstIntStream.max().orElseThrow();
        var max_y = Solution.secondIntStream.max().orElseThrow();

        return (Math.exp(Math.abs(max_x)) - Math.exp(Math.abs(max_y))) / Math.sqrt(Math.abs(max_x * max_y));
    }

    /**
     * 2. Вычислить M = (S+T+K)/2, где S, T, K – суммы положительных элементов массивов А, В, С соответственно.
     * Для вычисления суммы положительных элементов использовать функцию.
     *
     * @return значение полученного выражения
     */

    public static double secondQuestion() {
        // -> - это лямбда выражение, как в .net
        return (Solution.firstIntStream.filter(n -> n > 0).sum() +
                Solution.secondIntStream.filter(n -> n > 0).sum() +
                Solution.thirdIntStream.filter(n -> n > 0).sum()) / 2.0;
    }

    /**
     * 3. Даны целые числа m, n. Вычислить с = m!/(n! * (m-n)!).
     * Для вычисления факториала использовать функцию.
     * @return Возвращает целое число, то есть результат сочетания.
     */
    public static BigInteger thirdQuestion() {
        int m = 4, n = 2;
        // в Java, к сожалению, нет перегрузки операторов, поэтому тут математические действия делаются через методы
        // Взято с библиотеки
        return BigIntegerMath.factorial(m).divide(
                BigIntegerMath.factorial(n).multiply(BigIntegerMath.factorial(m - n)));
    }

    /**
     * 4. Даны действительные х, у, z. Составить программу вычисления значения
     * Math.sqrt(pow_x + pow_y + Math.pow(Math.sin(x * y), 2)) + Math.sqrt(pow_x + pow_z + Math.pow(Math.sin(x * z), 2))
     * + Math.sqrt(pow_z + pow_y + Math.pow(Math.sin(z * y), 2))
     */
    public static double fourthQuestion() {
        // Здесь я нашел библиотеку, которая генерирует комбинации из (1, 2, 3) по 2 элемента.
        // map здесь делается то же самое, но есть небольшие разновидности, которые переделывают нам элементы к
        // нужному типу данных. По умолчанию generator возвращает итератор, где каждый элемент - это список с числами.
        return Generator.combination(1, 2, 3).simple(2).stream().mapToDouble(x ->
                Math.sqrt(Math.pow(x.getFirst(), 2) + Math.pow(x.getLast(), 2) +
                        Math.pow(Math.sin(x.getFirst() * x.getLast()), 2))).sum();
    }

    /**
     * 5. Составить программу для вычисления среднего арифметического положительных элементов массивов Х(20), Y(15),
     * Z(10), используя в качестве подпрограммы функцию.
     *
     * @return Возвращает строку, где написаны средние значения в каждом массиве.
     */
    public static String fifthQuestion() {
        IntStream[] arrays = {firstIntStream, secondIntStream, thirdIntStream};
        // Нет аналога enumerate, поэтому воспользовался таким костылем.
        return "Реузльат 5 задания:\n" + IntStream.range(0, arrays.length)
                .mapToObj(index -> "Среднее значение массива " + (index + 1) + ": " +
                        String.format("%.3f", arrays[index].filter(x -> x > 0).average().orElseThrow()))
                .collect(Collectors.joining("\n"));
    }

    /**
     * 6. Даны массивы А(15), Y(15), C(12).
     * Вычислить l = min(b_i) + min(c_i) if abs(min(a_i)) > 10 else 1 + min(abs(c_i))
     */
    public static String sixthQuestion() {
        var result = Math.abs(firstIntStream.min().orElseThrow()) > 10 ?
                secondIntStream.min().orElseThrow() + thirdIntStream.min().orElseThrow() :
                1 + thirdIntStream.map(Math::abs).min().orElseThrow();
        return String.format("Результат 6 задания: %d", result);
    }

    /**
     * 7. Дан массив D(40) вещественных чисел. Найти среднее геометрическое его элементов,
     * которые удовлетворяют условию 0 < di <12. Для вычислений использовать функцию.
     */
    public static String seventhQuestion() {
        double[] D = new Random().doubles(40).toArray();
        var result = Math.pow(
                Arrays.stream(D).filter(x -> x > 0 && x < 12).reduce(1, (a, b) -> a * b),
                1.0 / D.length
        );

        return String.format("Результат 7 задания: %.3f", result);
    }

    /**
     * 8. Дан массив А(80) целых чисел.
     * Найти сумму и количество теx элементов массива, которые отрицательны и нечетны.
     * Использовать в качестве подпрограммы процедуру.
     */
    public static String eighthQuestion() {
        int[] A = new Random().ints(80).toArray();

        var result = Arrays.stream(A).filter(n -> n < 0 && Math.abs(n) % 2 == 1).sum();
        return "Результат 8 задания: " + result;
    }

    /**
     * 9. Функция, вычисляющая среднее арифметическое элементов массива.
     * Написать функцию, которая вычисляет среднее арифметическое элементов массива,
     * переданного ей в качестве аргумента
     */
    public static String ninthQuestion() {
        return "Результат 9 задания: " + Solution.firstIntStream.average().orElseThrow();
    }

    /**
     * 10. Отсортировать массив по возрастанию суммы цифр
     * Дан одномерный массив, состоящий из натуральных чисел.
     * Выполнить сортировку данного массива по возрастанию суммы цифр чисел.
     * Например, дан массив чисел [14, 30, 103]. После сортировки он будет таким: [30, 103, 14],
     * так как сумма цифр числа 30 составляет 3, числа 103 равна 4, числа 14 равна 5.
     */
    public static String tenthQuestion() {
        IntStream stream = new Random().ints(50, 1, 10000);
        var result = stream
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

        return "Результат 10 задания: " + Arrays.toString(result);
    }

    /**
     * 11. Вывести на экран исходный массив, отсортированный массив,
     * а также для контроля сумму цифр каждого числа отсортированного массива.
     */
    public static String eleventhQuestion() {
        var res = "Результат 11 задания:\n";
        var array = new Random().ints(50, 1, 10000).toArray();
        res += "Исходный массив: " + Arrays.toString(array) + "\n";
        res += "Отсортированный массив: " + Arrays.stream(array).boxed().sorted().toList() + "\n";
        res += "Сумма цифр каждого числа: " + Arrays.stream(array).map(n -> {

            int sumOfDigits = 0;
            n = Math.abs(n);

            while (n > 0) {
                sumOfDigits += n % 10;
                n /= 10;
            }
            return sumOfDigits;
        }).boxed().toList();

        return res;
    }

    /**
     * 12. Определить количество разрядов числа.
     * Написать функцию, которая определяет количество разрядов введенного целого числа.
     */
    public static String twelfthQuestion() {
        Scanner scanner = new Scanner(System.in);
        var n = Math.abs(scanner.nextInt());
        var p = n;

        int count = 0;

        while (n > 0) {
            count += 1;
            n /= 10;
        }

        return "Количество разрядов числа " + p + " равно: " + count;
    }

    /**
     * 13. Сумма ряда с факториалом. Вычислить сумму ряда
     */
    public static String thirteenthQuestion() {
        for (int i = 1; i < 6; i++) {
            
        }
        return "";
    }

}

