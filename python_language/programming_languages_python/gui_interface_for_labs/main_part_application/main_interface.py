"""
Главный интерфейс приложения.
Здесь описаны интерфейсы взаимодействия, сигналы и т.п.
"""

from PyQt6 import QtWidgets

from .custom_widgets.left_side_menu import TabWidget
from .page_logic import Page
from .tasks import TaskChooser, info_cur_dir_modules


class DlgMain(QtWidgets.QMainWindow):

    def __init__(self, parent):
        """
        Инициализация главного окна приложения.
        """
        super().__init__(parent)
        # создание всех страниц, которые мы инициализируем
        self.list_widget = []
        self.__all_questions = info_cur_dir_modules()
        # создание количества пунктов в боковом меню
        self._create_page(len(self.__all_questions))
        # сигнал для отслеживания изменений
        self.tabs.currentChanged.connect(self.update_condition)

    def _create_page(self, n) -> None:
        """
        Создание вертикального меню, где находятся лабораторные работы
        """
        self.tabs = TabWidget(self)
        self.tabs.create_tabs(n)
        self.setCentralWidget(self.tabs)

    def update_condition(self) -> None:
        """
        Обновление условия задачи при изменении страницы.
        """
        widget, current_laboratory, current_number_question = self.__get_info()
        condition = TaskChooser(current_laboratory, current_number_question).condition
        widget.label.setText(condition)
        widget.input_data.clear()
        widget.output_data.clear()

        self.__change_combobox(widget, current_laboratory)

    def on_ok_button_clicked(self) -> None:
        """
        Обработка сигнала нажатия "Ok"
        """
        widget, current_laboratory, current_number_question = self.__get_info()
        func = TaskChooser(current_laboratory, current_number_question).function
        text = "Выполнено" if func is None else str(func(widget.input_data.text()))
        widget.output_data.setText(text)

    def on_cancel_button_clicked(self):
        """
        Обработка сигнала нажатия "Cancel"
        """
        widget = self.__get_info()[0]
        widget.input_data.clear()
        widget.output_data.clear()

    def __get_info(self):
        """
        Вспомогательная функция, которая возвращает кортеж, состоящий из текущего номера лабораторной работы,
        номера вопроса задания
        """
        widget: Page = self.list_widget[self.tabs.currentIndex()]
        current_laboratory: int = self.tabs.currentIndex() + 1
        current_number_question: int = widget.combobox.currentIndex() + 1
        return widget, current_laboratory, current_number_question

    def __change_combobox(self, widget: Page, current_laboratory: int):
        """
        Метод для обновления данных в combobox
        Встроенный метод clear() вызывает ошибку памяти
        """
        current_index = widget.combobox.currentIndex()
        widget.combobox.blockSignals(True)  # Блокировка сигналов
        widget.combobox.clear()  # Полная очистка

        functions_count = len([func for func in self.__all_questions[current_laboratory - 1].__dict__.values()
                               if callable(func)])

        widget.combobox.addItems([f"Задание {x}" for x in range(1, functions_count + 1)])

        widget.combobox.setCurrentIndex(current_index)
        widget.combobox.blockSignals(False)  # Разблокировка сигналов