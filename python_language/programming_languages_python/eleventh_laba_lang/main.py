"""
AUTHOR: 1 вариант Ковалев Данил ВКБ22
"""
import os
import re
from pprint import pprint
from typing import Callable

########################################################################################################################
from sqlalchemy import create_engine, text
from sqlalchemy.orm import sessionmaker

########################################################################################################################
from db_creation import create_database
from db_creation.tables import Position, Department, Teacher
from db_interface import *
from csv_interaction import db_to_csv


def first_question(k=None):
    """
    Представить таблицы в виде структур языка Python
    """
    if not os.path.exists("database.db"):
        create_database()

    engine = create_engine('sqlite:///database.db', echo=False)
    session = sessionmaker(bind=engine)()
    # SQL-запрос для извлечения данных из нескольких таблиц
    # Join объединяет наши данные, чтобы они были вместе
    query_result = session.query(Teacher, Position, Department).join(Position).join(Department).all()

    data_dict = {}
    for teacher, position, department in query_result:
        data_dict[teacher.id] = {
            'name': teacher.name,
            'age': teacher.age,
            'position': {'id': position.id, 'title': position.title},
            'department': {'id': department.id, 'title': department.title, 'institute': department.institute}
        }

    return data_dict


def second_question(string: str) -> str:
    """
    Реализуйте в консоли интерфейс по добавлению, удалению, изменению данных.
    Имейте в виду, что связанные операции (удаление, добавление, изменение) для связанных таблиц,
    должны изменять данные во всех связанных структурах.
    """
    if not os.path.exists("database.db"):
        create_database()

    pattern_and_functions: list[tuple[re.Pattern, Callable]] = [
        (re.compile(r'^Удалить (преподавателя|препода)? (.+)$', re.I | re.U), remove_by_name),
        (re.compile(r"^Удалить всех с кафедры (.+)$", re.I | re.U), remove_by_department),
        (re.compile(r"^Удалить всех,? кто имеет звание -(.+)$", re.I | re.U), remove_by_position),
        (re.compile(r"^Добавить новое звание - (.+)$", re.I | re.U), add_new_position),
        (re.compile(r"^Добавить новую кафедру[:\-]? (.+) в университет (.+)$", re.I | re.U), add_new_department),
        (re.compile(r"^Добавить нового преподавателя (.+) с возрастом (\d+) на кафедру (.+) в университет (.+)"
                    r" на должность (.+)$", re.I | re.U), add_new_teacher),
        (re.compile(r"^Изменить имя преподавателя с (.+) на (.+)$", re.I | re.U), change_teacher_name),
        (re.compile(r"^Изменить название кафедры с (.+) на (.+)$", re.I | re.U), change_department)
    ]

    for pattern, func in pattern_and_functions:
        if res := pattern.fullmatch(string.strip()):
            return f"'{string}' - выполнено! " if func(res.groups()) else "Не выполнено!"
    return f"Неправильный ввод данных"


def third_question(k=None):
    """
    Вывести построчно информацию для каждого преподавателя:
    "ФИО преподавателя", "название кафедры", "должность"
    """
    if not os.path.exists("database.db"):
        create_database()

    engine = create_engine('sqlite:///database.db', echo=False)
    session = sessionmaker(bind=engine)()

    # SQL код выполняется не построчно, как Python.
    # Здесь такая последовательность FROM -> JOIN -> SELECT
    query = text(
        """
        SELECT teachers.name, departments.title AS department_title, positions.title AS position_title
        FROM teachers
        JOIN departments ON teachers.department_id = departments.id
        JOIN positions ON teachers.position_id = positions.id
        """
    )

    result = session.execute(query)

    return "\n".join(
        f"ФИО - {row[0]}\n"
        f"Название кафедры - {row[1]}\n"
        f"Должность преподавателя - {row[2]}\n"
        for row in result
    )


def fourth_question(k=None):
    """
    Посчитайте и выведите результат:
    Для каждой кафедры: сколько всего преподавателей.
    """
    if not os.path.exists("database.db"):
        create_database()

    engine = create_engine('sqlite:///database.db', echo=False)
    session = sessionmaker(bind=engine)()

    query = text(
        """
        SELECT departments.title AS department_title, COUNT(teachers.id) AS teacher_count
        FROM teachers
        JOIN departments ON teachers.department_id = departments.id
        GROUP BY teachers.department_id;
        """
    )

    result = session.execute(query)

    return '\n'.join(f"Кафедра: {row[0]}, Количество преподавателей: {row[1]}" for row in result)


def fifth_question(decree: str) -> str:
    """
    Реализуйте функционал по сохранению данных в файлы формата .csv и считыванию информации из файлов
    :param decree: ввод от пользователя, то есть что он хочет сделать.
    """
    if re.fullmatch(r"(Записать|Вписать)\s?(данные)?", decree, re.I):
        res: dict = first_question()
        db_to_csv(res)
        return "Готово"

    elif re.fullmatch(r"(Считать|Прочитать)\s?(данные)?", decree, re.I):
        ...




def main() -> None:
    match input("Выберите номер задания: "):
        case "1":
            pprint(first_question())
        case "2":
            print(second_question(input("Введите ваше пожелание, как описано в doc ")))
        case "3":
            print(third_question())
        case "4":
            print(fourth_question())
        case "5":
            print(fifth_question(input("Введите что вы сделать: записать или считать данные ")))
        case _:
            print("Вы выбрали неверное задание ")


if __name__ == "__main__":
    main()
