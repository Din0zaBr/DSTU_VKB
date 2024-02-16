package programmingLanguagesJava.laboratories.fourthLaboratory;

import programmingLanguagesJava.laboratories.ConsoleReader;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {

    static SingleLinkedList<Integer> list = initialize();
    static DoubleLinkedList<Integer> doubleList = initializeDouble();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите номер задания: ");
        var question = scanner.nextInt();

        System.out.printf("---------------------------------------------------\nРезультат %d задания:\n", question);

        Object result = switch (question) {

            case 1, 4, 5, 6, 7, 8, 9, 15, 16, 17, 18, 19,
                    21, 24, 25, 26, 27, 28, 35, 36, 37, 38, 39 ->
                    ConsoleReader.executeTask(Solution.class, String.valueOf(question), " ");

            case 2, 3, 10,
                    22, 23, 30 -> {
                System.out.println("Введите число для задания: ");
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.next());
            }

            case 11, 31 -> {
                System.out.print("Какое вы хотите найти максимальное или минимальное? ");
                scanner.nextLine();
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.nextLine());
            }

            case 12, 13, 32, 33 -> {
                System.out.print("Введите значение для удаления: ");
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.next());
            }

            case 14, 34 -> {
                System.out.print("Введите два значения (старое, новое): ");
                scanner.nextLine();
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.nextLine());
            }

            case 20, 40 -> {
                System.out.println("Как вы хотите сортировать? С помощью указателей или значений? (pointer, data): ");
                scanner.nextLine();
                yield ConsoleReader.executeTask(Solution.class, String.valueOf(question), scanner.nextLine());
            }

            default -> "Вы выбрали неверное задание";
        };

        scanner.close();
        System.out.println(result);

        }

    /**
     * Инициализация списка
     */
    @SuppressWarnings("unused")
    public static String firstQuestion(String ignoreUnused) {
        var localList = new SingleLinkedList<>();
        return "Список был инициализирован";
    }

    /**
     * Добавление элемента в начало списка
     */
    @SuppressWarnings("unused")
    public static String secondQuestion(String number) {
        list.addFirst(Integer.valueOf(number.strip()));
        return "Наш связный список после добавления элемента в начало: " + list;
    }

    /**
     * Добавление элемента в конец списка
     */
    @SuppressWarnings("unused")
    public static String thirdQuestion(String number) {
        list.add(Integer.valueOf(number.strip()));
        return "Наш связный список после добавления элемента в конец: " + list;
    }

    /**
     * Показ всех элементов списка
     */
    @SuppressWarnings("unused")
    public static String fourthQuestion(String ignoreUnused) {
        return "Наш связный список: " + list;
    }

    /**
     * Удаление всех элементов списка
     */
    @SuppressWarnings("unused")
    public static String fifthQuestion(String ignoreUnused) {

        var result = "Добавил случайные элементы в связный список: " + list;
        list.clear();
        result += "\nПосле очистки: " + list;

        return result;
    }

    /**
     * Определение количества элементов списка
     */
    @SuppressWarnings("unused")
    public static String sixthQuestion(String ignoreUnused) {
        return String.format("Добавил случайные элементы в связный список: %s\nКоличество элементов: %d", list, list.size());
    }

    /**
     * Проверка списка на пустоту
     */
    @SuppressWarnings("unused")
    public static String seventhQuestion(String ignoreUnused) {
        return String.format("Наш список: %s\nПустота: %s", list, list.isEmpty());
    }

    /**
     * Удаление первого элемента
     */
    @SuppressWarnings("unused")
    public static String eighthQuestion(String ignoreUnused) {
        var result = "Наш список: " + list;
        list.delFirst();
        result += "\nПосле удаления первого элемента: " + list;
        return result;
    }

    /**
     * Удаление последнего элемента
     */
    @SuppressWarnings("unused")
    public static String ninthQuestion(String ignoreUnused) {
        var result = "Наш список: " + list;
        list.delLast();
        result += "\nПосле удаления последнего элемента: " + list;
        return result;

    }

    /**
     * Поиск данного значения в списке
     */
    @SuppressWarnings("unused")
    public static String tenthQuestion(String number) {
        var list = new SingleLinkedList<Integer>();
        IntStream.range(1, 10).forEach(list::add);

        return String.format(
                "Значение %s находится под индексом %d в %s",
                number,
                list.indexOf(Integer.parseInt(number)),
                list
        );
    }

    /**
     * Поиск наибольшего и наименьшего значений в списке
     */
    @SuppressWarnings("unused")
    public static String eleventhQuestion(String argue) {
        return switch (argue.toLowerCase()) {
            case "наибольшее", "максимальное" -> String.format("%d - максимальное значение в %s", list.max(), list);
            case "наименьшее", "минимальное" -> String.format("%d - минимальное значение в %s", list.min(), list);
            default -> "Вы ввели неправильный аргумент к методу";
        };

    }

    /**
     * Удаление элемента списка с данным значением
     */
    @SuppressWarnings("unused")
    public static String twelfthQuestion(String value) {
        var list = new SingleLinkedList<Integer>();
        IntStream.range(1, 10).forEach(list::add);

        var result = "Список до: " + list;
        list.remove((Integer) Integer.parseInt(value));
        result += "\nПосле: " + list;

        return result;
    }

    /**
     * Удаление всех элементов списка с данным значением
     */
    @SuppressWarnings("unused")
    public static String thirteenthQuestion(String string) {
        var list = new SingleLinkedList<Integer>();
        IntStream.range(1, 10).forEach(x -> {
            list.add(x);
            list.add(x);
            list.add(x);
        });

        var result = "Список до: " + list;
        list.removeAll(Integer.parseInt(string));
        result += "\nПосле: " + list;

        return result;
    }

    /**
     * Изменение всех элементов списка с данным значением на новое.
     */
    @SuppressWarnings("unused")
    public static String fourteenthQuestion(String args) {

        var list = new SingleLinkedList<Integer>();
        IntStream.range(1, 10).forEach(x -> {
            list.add(x);
            list.add(x);
            list.add(x);
        });

        var it = Arrays.stream(args.split("\\s+")).map(Integer::valueOf).iterator();

        var result = "Список до: " + list;
        list.replace(it.next(), it.next());
        result += "\nПосле: " + list;

        return result;
    }

    /**
     * Определение, является ли список симметричным.
     */
    @SuppressWarnings("unused")
    public static String fifteenthQuestion(String ignoreUnused) {
        var symList = new SingleLinkedList<Integer>();

        IntStream.range(1, 11).forEach(symList::add);
        IntStream.iterate(9, i -> i - 1).limit(9).forEach(symList::add);

        return String.format("Список %s - симметричен (%s)", symList, symList.isSymmetric());
    }

    /**
     * Определение, можно ли удалить из списка каких-нибудь два элемента так, чтобы новый список оказался упорядоченным.
     */
    @SuppressWarnings("unused")
    public static String sixteenthQuestion(String ignoreUnused) {
        var list = new SingleLinkedList<Integer>();

        var firstTest = Arrays.asList(30, 40, 2, 5, 1, 7, 45, 50, 8);
        var secondTest = Arrays.asList(2, 7, 1, 10, 4);

        secondTest.forEach(list::add);

        return String.format("Список: %s\nМожет быть отсортирован 2 удалениями: %s", list, list.canBeSortedByDeleting2());
    }

    /**
     * Определение, сколько различных значений содержится в списке.
     */
    @SuppressWarnings("unused")
    public static String seventeenthQuestion(String ignoreUnused) {
        var list = new SingleLinkedList<Integer>();

        var values = Arrays.asList(30, 40, 2, 5, 2, 7, 30, 40, 8);
        values.forEach(list::add);

        return String.format("Список: %s\nКоличество разных значений: %d", list, list.countDistinct());
    }

    /**
     * Удаление из списка элементов, значения которых уже встречались в предыдущих элементах.
     */
    @SuppressWarnings("unused")
    public static String eighteenthQuestion(String ignoreUnused) {
        var list = new SingleLinkedList<Integer>();

        var values = Arrays.asList(30, 40, 2, 5, 2, 7, 30, 40, 8);
        values.forEach(list::add);

        return String.format("Список до: %s\nСписок после: %s", list, list.distinct());
    }

    /**
     * Изменение порядка элементов на обратный.
     */
    @SuppressWarnings("unused")
    public static String nineteenthQuestion(String ignoreUnused) {
        var result = "";

        result += "Список исходный: " + list;
        list.reversed();
        result += "\nРазвернутый список: " + list;
        return result;
    }

    /**
     * Сортировка элементов списка двумя способами (изменение указателей, изменение значений элементов)
     */
    @SuppressWarnings("unused")
    public static String twentiethQuestion(String param) {
        var result = "Список: " + list;
        list.sort(param);
        result += "\nСписок после " + param + " сортировки: " + list;
        return result;
    }

    /**
     * Инициализация двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentyFirstQuestion(String ignoreUnused) {
        return "Двусвязный список был инициализирован";
    }


    /**
     * Добавление элемента в начало двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentySecondQuestion(String number) {
        doubleList.addFirst(Integer.valueOf(number.strip()));
        return "Наш связный список после добавления элемента в начало: " + doubleList;
    }

    /**
     * Добавление элемента в конец двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentyThirdQuestion(String number) {
        doubleList.add(Integer.valueOf(number.strip()));
        return "Наш связный список после добавления элемента в конец: " + doubleList;
    }

    /**
     * Показ всех элементов двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentyFourthQuestion(String ignoreUnused) {
        return "Наш связный список: " + doubleList;
    }

    /**
     * Удаление всех элементов двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentyFifthQuestion(String ignoreUnused) {

        var result = "Добавил случайные элементы в связный список: " + doubleList;
        doubleList.clear();
        result += "\nПосле очистки: " + doubleList;

        return result;
    }

    /**
     * Определение количества элементов двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentySixthQuestion(String ignoreUnused) {
        return String.format(
                "Добавил случайные элементы в связный список: %s\nКоличество элементов: %d",
                doubleList,
                doubleList.size()
        );
    }

    /**
     * Проверка двусвязного списка на пустоту
     */
    @SuppressWarnings("unused")
    public static String twentySeventhQuestion(String ignoreUnused) {
        return String.format("Наш список: %s\nПустота: %s", doubleList, doubleList.isEmpty());
    }

    /**
     * Удаление первого элемента двусвязного списка
     */
    @SuppressWarnings("unused")
    public static String twentyEighthQuestion(String ignoreUnused) {
        var result = "Наш список: " + doubleList;
        doubleList.delFirst();
        result += "\nПосле удаления первого элемента: " + doubleList;
        return result;
    }

    /**
     * Удаление последнего элемента двусвязного списка.
     */
    @SuppressWarnings("unused")
    public static String twentyNinthQuestion(String ignoreUnused) {

        var result = "Наш список: " + doubleList;
        doubleList.delFirst();
        result += "\nПосле удаления первого элемента: " + doubleList;
        return result;
    }

    /**
     * Поиск данного значения в двусвязном списке
     */
    @SuppressWarnings("unused")
    public static String thirtyQuestion(String number) {

        return String.format(
                "Значение %s находится под индексом %d в %s",
                number,
                doubleList.indexOf(Integer.parseInt(number)),
                doubleList
        );
    }

    /**
     * Поиск наибольшего и наименьшего значений в списке
     */
    @SuppressWarnings("unused")
    public static String thirtyFirstQuestion(String argue) {
        return switch (argue.toLowerCase()) {
            case "наибольшее", "максимальное" -> String.format("%d - максимальное значение в %s", doubleList.max(), doubleList);
            case "наименьшее", "минимальное" -> String.format("%d - минимальное значение в %s", doubleList.min(), doubleList);
            default -> "Вы ввели неправильный аргумент к методу";
        };

    }

    /**
     * Удаление элемента двусвязного списка с данным значением
     */
    @SuppressWarnings("unused")
    public static String thirtySecondQuestion(String value) {
        var result = "Список до: " + doubleList;
        doubleList.remove(Integer.parseInt(value));
        result += "\nПосле: " + doubleList;

        return result;
    }

    /**
     * Удаление всех элементов списка с данным значением
     */
    @SuppressWarnings("unused")
    public static String thirtyThirdQuestion(String string) {
        var result = "Список до: " + doubleList;
        doubleList.removeAll(Integer.parseInt(string));
        result += "\nПосле: " + doubleList;

        return result;
    }

    /**
     * Изменение всех элементов списка с данным значением на новое.
     */
    @SuppressWarnings("unused")
    public static String thirtyFourthQuestion(String args) {
        var it = Arrays.stream(args.split("\\s+")).map(Integer::valueOf).iterator();

        var result = "Список до: " + doubleList;
        doubleList.replace(it.next(), it.next());
        result += "\nПосле: " + doubleList;

        return result;
    }


    /**
     * Определение, является ли список симметричным.
     */
    @SuppressWarnings("unused")
    public static String thirtyFifthQuestion(String ignoreUnused) {
        var symList = new DoubleLinkedList<Integer>();

        IntStream.range(1, 10).forEach(symList::add);
        IntStream.range(9, 0).forEach(symList::add);

        return String.format("Список %s - симметричен (%s)", symList, symList.isSymmetric());
    }

    /**
     * Определение, можно ли удалить из двусвязного списка каких-нибудь два элемента так, чтобы новый список оказался упорядоченным.
     */
    @SuppressWarnings("unused")
    public static String thirtySixthQuestion(String ignoreUnused) {
        var list = new DoubleLinkedList<Integer>();

        var firstTest = Arrays.asList(30, 39, 2, 5, 1, 7, 45, 50, 8);
        var secondTest = Arrays.asList(2, 7, 1, 10, 4);

        secondTest.forEach(list::add);

        return String.format("Список: %s\nМожет быть отсортирован 2 удалениями: %s", list, list.canBeSortedByDeleting2());
    }

    /**
     * Определение, сколько различных значений содержится в двусвязном списке.
     */
    @SuppressWarnings("unused")
    public static String thirtySeventhQuestion(String ignoreUnused) {
        var list = new DoubleLinkedList<Integer>();

        var values = Arrays.asList(30, 40, 2, 5, 2, 7, 30, 40, 8);
        values.forEach(list::add);

        return String.format("Список: %s\nКоличество разных значений: ", list.countDistinct());
    }

    /**
     * Удаление из списка элементов, значения которых уже встречались в предыдущих элементах.
     */
    @SuppressWarnings("unused")
    public static String thirtyEighthQuestion(String ignoreUnused) {
        var list = new DoubleLinkedList<Integer>();

        var values = Arrays.asList(30, 40, 2, 5, 2, 7, 30, 40, 8);
        values.forEach(list::add);

        return String.format("Список: %s\nКоличество разных значений: ", list.distinct());
    }

    /**
     * Изменение порядка элементов на обратный.
     */
    @SuppressWarnings("unused")
    public static String thirtyNinthQuestion(String ignoreUnused) {
        var result = "";

        result += "Список исходный: " + doubleList;
        doubleList.reversed();
        result += "\nРазвернутый список: " + doubleList;
        return result;
    }

    /**
     * Сортировка элементов списка двумя способами (изменение указателей, изменение значений элементов)
     */
    @SuppressWarnings("unused")
    public static String fortiethQuestion(String param) {
        var result = "Список: " + doubleList;
        doubleList.sort(param);
        result += "\nСписок после сортировки вашим способом: " + doubleList;
        return result;
    }




    private static SingleLinkedList<Integer> initialize() {
        var list = new SingleLinkedList<Integer>();
        new Random().ints(10, 5, 1000).forEach(list::add);
        return list;
    }

    private static DoubleLinkedList<Integer> initializeDouble() {
        var list = new DoubleLinkedList<Integer>();
        new Random().ints(10, 5, 1000).forEach(list::add);
        return list;
    }

}
