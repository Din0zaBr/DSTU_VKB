/**
 * Здесь у меня перечисление для удобства.
 */
// Перечисление запросов SQL (перечисление - сборник констант и всё "общего", zb сущность "операция" содержит перечисление +, -, : и тд
package programmingLanguagesJava.laboratories.GUI.controllers.project.database.utils;
// Занос

/**
 * Здесь содержаться готовые SQL запросы, которые я буду использовать.
 */
public enum SQLQuery {
    ;
    // Вставка в таблицу Remaining_info
    public static final String INSERT_REMAINING = "INSERT INTO Remaining_info (document, plan_of_house) VALUES (?, ?)";
    // Вставка в таблицу новых Peoples
    public static final String INSERT_HUMAN = "INSERT INTO Peoples (last_name, first_name, patronymic, post, remaining_info) VALUES (?, ?, ?, ?, ?)";
    // Поиск максимального индекса среди обновлённых в базе данных
    public static final String MAX_ID = "SELECT MAX(id) FROM Remaining_info";

    public static final String JOIN_TABLES = """
            SELECT Peoples.first_name,
                   Peoples.last_name,
                   Peoples.patronymic,
                   Remaining_info.plan_of_house,
                   Remaining_info.document

            FROM Peoples

            JOIN Remaining_info ON Peoples.remaining_info = Remaining_info.id

            """;
    // вместо 4 становятся те элементы, который находится в полях Remaining_info.id (SELECT * FROM Remaining_info)
}
