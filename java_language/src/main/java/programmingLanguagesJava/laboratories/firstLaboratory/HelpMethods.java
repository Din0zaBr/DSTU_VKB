package programmingLanguagesJava.laboratories.firstLaboratory;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class HelpMethods {
    /**
     * Функция для вычисления наибольшего общего делителя (НОД) двух целых чисел с использованием алгоритма Штейна.
     * Алгоритм Штейна для вычисления наибольшего общего делителя (НОД) двух целых чисел основан на принципе, что
     * НОД двух чисел равен НОДей их разности и НОДей их остатков от деления на друг друга
     * Этот алгоритм использует рекурсивный подход для последовательного уменьшения чисел до тех пор,
     * пока они не станут равными, что и будет их НОДем.
     *
     * @param a первое целое число для поиска НОД
     * @param b второе целое число для поиска НОД
     * @return НОД двух входных целых чисел
     */
    static int algorithm_stein(int a, int b) {

        // GCD(0, b) == b; GCD(a, 0) == a,
        // GCD(0, 0) == 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // Finding K, where K is the greatest
        // power of 2 that divides both a and b
        int k;
        for (k = 0; ((a | b) & 1) == 0; ++k) {
            a >>= 1;
            b >>= 1;
        }

        // Dividing a by 2 until a becomes odd
        while ((a & 1) == 0)
            a >>= 1;

        // From here on, 'a' is always odd.
        do {
            // If b is even, remove
            // all factor of 2 in b
            while ((b & 1) == 0)
                b >>= 1;

            // Now a and b are both odd. Swap
            // if necessary so a <= b, then set
            // b = b - a (which is even)
            if (a > b) {
                // Swap u and v.
                int temp = a;
                a = b;
                b = temp;
            }

            b = (b - a);
        } while (b != 0);

        // restore common factors of 2
        return a << k;
    }

    /**
     * Вспомогательный статический метод, который используется для создания случайной матрицы.
     *
     * @param CountRows    количество строк в матрице.
     * @param CountColumns количество колонок в матрице.
     * @return Возвращает матрицу
     */

    static int[][] generateRandomMatrix(int CountRows, int CountColumns) {
        Random random = new Random();
        return Arrays.stream(new int[CountRows][CountColumns])
                .map(row -> Arrays.stream(row)
                        .map(col -> random.nextInt(100)) // Здесь 100 - верхняя граница случайных чисел
                        .toArray())
                .toArray(int[][]::new);
    }

    /**
     * Преобразует двумерный массив целых чисел в строковое представление.
     *
     * @param matrix двумерный массив целых чисел для преобразования
     * @return строковое представление двумерного массива
     */
    static String ToString(int[][] matrix) {
        return Arrays.stream(matrix)
                .map(x -> Arrays.stream(x)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" ")))
                // объединяет строки, которые были созданы в результате обработки каждой строки двумерного массива,
                // используя разделитель строк, который зависит от операционной системы
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
