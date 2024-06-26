package programmingLanguagesJava.laboratories;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleReader {

    private static final RuleBasedNumberFormat numberFormat = new RuleBasedNumberFormat(Locale.UK, RuleBasedNumberFormat.SPELLOUT);
    // Locale.UK для определения специфических правил форматирования чисел, таких как порядок цифр, разделители и т. д.,
    // соответствующих британской локали (специфическая региональная настройка)


    /**
     * @param solutionClass    Объект класса, представляющий класс решения
     * @param numberOfQuestion Строка, представляющая номер вопроса
     * @param args             Переменное количество аргументов, передаваемых в метод
     * @return Объект, возвращаемый выполнением метода
     */
    public static Object executeTask(Class<?> solutionClass, String numberOfQuestion, Object... args) {
        try {
            // Ординальное число - это числительное, которое указывает на порядок следования чего-либо в последовательности.
            // Например, "первый",
            var methodName = numberFormat.format(Integer.parseInt(numberOfQuestion), "%spellout-ordinal") + "Question";

            // паттерн для поиска в строке символа "-", за которым следует буква.
            Pattern pattern = Pattern.compile("-(\\w)");
            Matcher matcher = pattern.matcher(methodName);

            StringBuilder str = new StringBuilder();

            while (matcher.find()) {
                matcher.appendReplacement(str, matcher.group(1).toUpperCase()); // CamelCase
            }
            matcher.appendTail(str);

            // Формируется массив paramTypes, содержащий типы аргументов, переданных в метод.
            // С помощью рефлексии получается метод из класса solutionClass с соответствующим именем и типами аргументов.
            Class<?>[] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                // Для каждого аргумента определяется его тип и добавляется в массив.
                paramTypes[i] = args[i].getClass();
            }
            // Вызывается найденный метод с переданными аргументами и возвращается результат выполнения.
            /*
             * p.s Используя рефлексию, находится метод с именем, сформированным в строке str, и типами аргументов из массива paramTypes
             * Затем этот метод вызывается на объекте solutionClass с переданными аргументами args и возвращается результат выполнения.
             */
            Method method = solutionClass.getMethod(str.toString(), paramTypes);
            return method.invoke(solutionClass.getDeclaredConstructor().newInstance(), args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException e) {
            return "Вы выбрали неверное задание";
        }
    }
}
