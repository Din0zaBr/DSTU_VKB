package programmingLanguagesJava.laboratories.firstLaboratory;

public class Triangle {
    /**
     * Вычисляет площадь треугольника
     *
     * @param  h    высота треугольника
     * @param  osn  основание треугольника
     * @return      площадь треугольника
     */
    public static String square(int h, int osn) {
        return String.format("Площадь треугольника - %f", (h * osn) / 2.0);
    }
}