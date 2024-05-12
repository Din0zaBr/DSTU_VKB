package programmingLanguagesJava.laboratories.GUI.controllers.project.database.utils;
// привязанность человека к документам. Не record, тк здесь использована библиотека lombok. В процессе работы кода создают геттеры и сеттеры, конструкторы, ...
import lombok.Data;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Data
public class PersonInfo {
    private String firstName;
    private String lastName;
    private String patronymic;
    private byte[] planOfHouse;
    private byte[] document;
}
