#!/usr/bin/python3
# -*- coding: utf-8 -*-
"""
Ковалев Данил ВКБ22 Вариант 1

Данный файл является основным, отсюда происходит запуск контента.
"""
import sys

from PyQt6 import QtCore, QtGui, QtWidgets
from tabdemo import TabWidget
from tasks import TaskChooser


class DlgMain(QtWidgets.QMainWindow):
    """
    Главный класс, здесь описывается основное окно, с которым взаимодействуем.
    """

    def __init__(self):
        super().__init__()
        self._init_ui()  # установка параметров для приложения
        self._create_tabs_vertical(6)  # создание количества пунктов в боковом меню
        self._create_combobox()  # создание выпадающего списка
        self._create_question()
        self._create_input_output_data()

        self.tabs.currentChanged.connect(self._update_condition)
        self.combobox.currentIndexChanged.connect(self._update_condition)

    def _init_ui(self):
        """
        Метод для инициализации параметров приложения
        """
        self.setWindowTitle("Ковалев Данил ВКБ22")  # установка названия приложения
        self.setWindowIcon(QtGui.QIcon("icons8-окно-приложения-96.png"))  # установка окна приложения
        self.resize(800, 500)  # Размеры по умолчанию при запуске
        self.setFont(QtGui.QFont('Cantrell', 11))  # Установка шрифта и размеров
        self._center()  # Центрирование приложение при запуске

    def _center(self):
        """
        Метод, который центрует положения появления окна при запуске.
        """
        qr = self.frameGeometry()  # размеры нашего окна
        cp = QtGui.QGuiApplication.primaryScreen().availableGeometry().center()
        qr.moveCenter(cp)
        self.move(qr.topLeft())

    def _create_tabs_vertical(self, n):
        """
        Создание вертикального меню, где находятся лабораторные работы
        """
        self.tabs = TabWidget()
        self.tabs.create_tabs(n)
        self.setCentralWidget(self.tabs)

    def _create_combobox(self):
        """
        Создание выпадающего списка, предположительно должен находится в правом верхнем угле
        """
        self.combobox = QtWidgets.QComboBox()  # создание выпадающего списка, гле будут задания
        self.combobox.addItems(["Задание 1", "Задание 2", "Задание 3",
                                "Задание 4"])  # в каждой лабораторной 4 задания, поэтому везде будет так

        self.tabs.layout.addWidget(self.combobox,
                                   alignment=QtCore.Qt.AlignmentFlag.AlignRight | QtCore.Qt.AlignmentFlag.AlignTop)

    def _create_question(self):
        """
        Создание места для условия, динамически должен обновляться в зависимости от выбора в combobox и TabWidget
        """
        self.label = QtWidgets.QLabel(
            "Напишите программу для решения примера. Есть переменные: a,b,c,k: Предусмотрите деление на 0. "
            "\nВсе необходимые переменные вводите ниже.")
        self.tabs.layout.addWidget(self.label,
                                   alignment=QtCore.Qt.AlignmentFlag.AlignRight | QtCore.Qt.AlignmentFlag.AlignTop)

    def _create_input_output_data(self):
        self.input_data = QtWidgets.QLineEdit()
        self.input_data.setPlaceholderText("Input data: ")

        self.output_data = QtWidgets.QTextEdit()
        self.output_data.setPlaceholderText("Output data: ")
        self.output_data.setReadOnly(True)

        splitter = QtWidgets.QSplitter(QtCore.Qt.Orientation.Vertical)
        splitter.addWidget(self.input_data)
        splitter.addWidget(self.output_data)

        self.tabs.layout.addWidget(splitter,
                                   alignment=QtCore.Qt.AlignmentFlag.AlignTop | QtCore.Qt.AlignmentFlag.AlignRight)

    def _update_condition(self):
        current_laboratory = self.tabs.currentIndex() + 1
        current_number_question = self.combobox.currentIndex() + 1
        condition = TaskChooser(current_laboratory, current_number_question).condition
        self.label.setText(condition)
        self.input_data.clear()

    def closeEvent(self, event):
        """
        Диалоговое окно, оно появляется, когда пользователь хочет закрыть приложение
        """
        res = QtWidgets.QMessageBox.question(self, "Выход", "Вы точно уверены, что хотите выйти? ")
        event.accept() if res == 16384 else event.ignore()


def main():
    app = QtWidgets.QApplication(sys.argv)
    dlg_window = DlgMain()
    dlg_window.show()
    sys.exit(app.exec())


if __name__ == "__main__":
    main()
