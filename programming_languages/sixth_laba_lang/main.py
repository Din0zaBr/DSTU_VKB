#!/usr/bin/env python
"""
Вариант 1 Ковалев Данил ВКБ22
"""
import npyscreen
import os


class TerminalForm(npyscreen.ActionPopupWide):

    def create(self):
        self.show_atx, self.show_aty = map(lambda x: (x[0] - x[1]) // 2,
                                           zip(os.get_terminal_size(), self.useable_space()[::-1]))
        self.add(npyscreen.TitleText, name="Выберите лабораторную работу: ", editable=False)

        lab_tree_data = npyscreen.TreeData(content="Лабораторные работы")
        for i in range(1, 6):
            parent_node = lab_tree_data.new_child(content=f"Лабораторная работа {i}")
            for j in range(1, 5):
                parent_node.new_child(content=f"Подзадача {j}")

        self.lab_tree = self.add(npyscreen.MLTreeMultiSelect, max_height=8, name="Выберите лабораторную работу:",
                                 values=lab_tree_data, scroll_exit=True)

        self.description = self.add(npyscreen.MultiLineEdit, value=" ", editable=False, rely=1, relx=-130)

    def on_ok(self):
        dictionary = {
            "Лабораторная работа 1 Подзадача 1": "Напишите программу для решения примера. Предусмотрите деление на 0. "
                                                 "\nВсе необходимые переменные вводите ниже",
            "Лабораторная работа 1 Подзадача 2": "Дан произвольный список, содержащий и строки, и числа. \n"
                                                 "Выведите все четные элементы построчно. ",
            "Лабораторная работа 1 Подзадача 3": "Дан произвольный список, содержащий только числа. \n "
                                                 "Выведите результат сложения чисел больше 10. ",
            "Лабораторная работа 1 Подзадача 4": "Дан произвольный список, содержащий только числа. \n"
                                                 "Выведите максимальное число",
            "Лабораторная работа 2 Подзадача 1": "Пусть задано некоторое число my_number. \n"
                                                 "Запрашивайте у пользователя вводить число user_number до тех пор, "
                                                 "пока оно не будет меньше my_number",
            "Лабораторная работа 2 Подзадача 2": "Пусть задан список, содержащий строки. \n"
                                                 "Выведите построчно все строки размером от 5 до 10 символов. ",
            "Лабораторная работа 2 Подзадача 3": "Сгенерируйте и выведите случайную строку, состоящую из 5 символов, \n"
                                                 "содержащую только заглавные буквы русского алфавита",
            "Лабораторная работа 2 Подзадача 4": "Пусть дана строка. На основе данной строки сформируйте новую, "
                                                 "содержащую только цифры \nВыведите новую строку. ",
            "Лабораторная работа 3 Подзадача 1": "Пусть дана строка, состоящая из слов, пробелов и знаков препинания. "
                                                 "\n"
                                                 "На основании этой строки создайте новую (и выведите её на консоль) \n"
                                                 "Содержащую только слова больше 5 символов.",
            "Лабораторная работа 3 Подзадача 2": "Пусть дана строковая переменная, содержащая информацию о студентах: "
                                                 "\n"
                                                 "my_string='Ф;И;О;Возраст;Категория;_Иванов;Иван;Иванович;23 года'",
            "Лабораторная работа 3 Подзадача 3": "Дана строка из прошлого задания. Выведите построчно информацию о "
                                                 "студентах, чья фамилия 'Петров'",
            "Лабораторная работа 3 Подзадача 4": "Пусть дана строка произвольной длины. Выведите информацию о том, "
                                                 "сколько в ней символов и сколько строк."
        }
        question_value = (selected_node := list(self.lab_tree.get_selected_objects())[0]).content
        parent_value = selected_node.get_parent().content
        self.description.value = dictionary[parent_value + ' ' + question_value]
        self.display()

    def on_cancel(self):
        if npyscreen.notify_yes_no("Вы хотите завершить работу программы? "):
            self.parentApp.setNextForm(None)
        else:
            self.parentApp.setNextForm("MAIN")


class Terminal(npyscreen.NPSAppManaged):
    def onStart(self):
        npyscreen.setTheme(npyscreen.Themes.ColorfulTheme)
        self.addForm('MAIN', TerminalForm, name="Выполнил Ковалев Данил ВКБ22 Вариант 1")


if __name__ == '__main__':
    TestApp = Terminal().run()
