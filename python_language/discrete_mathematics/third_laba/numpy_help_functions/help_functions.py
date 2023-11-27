"""
Данный модуль используется, как вспомогательный, здесь будут храниться основные операции над матрицами
"""
__all__ = ["up_shift_column", "down_shift_column", "rotate_bits"]

import numpy as np


def up_shift_column(array: np.ndarray, index: int, n: int) -> np.ndarray:
    """
    Сдвигает столбец с индексом index на n позиций вверх с использованием numpy.roll(column, -n)

    :param array: Входная матрица.
    :param index: Индекс столбца для сдвига.
    :param n: Количество позиций для циклического сдвига вверх.

    :return: Измененная матрица с выполненным сдвигом.
    Пример есть в тестах
    """
    # Извлекаем столбец с индексом index из матрицы array
    col = array[:, index]

    # Выполняем циклический сдвиг столбца на -n позиций вверх
    shift_col = np.roll(col, -n)

    # Заменяем столбец в матрице array сдвинутым столбцом
    for i in range(len(array)):
        array[i][index] = shift_col[i]

    # Возвращаем измененную матрицу
    return array


def down_shift_column(array: np.ndarray, index: int, n: int) -> np.ndarray:
    """
    Сдвигает столбец с индексом index на n позиций вниз с использованием numpy.roll(column, n)

    :param array: Входная матрица.
    :param index: Индекс столбца для сдвига.
    :param n: Количество позиций для циклического сдвига вниз.

    :return: Измененная матрица с выполненным сдвигом.
    Примеры есть в тестах
    """
    return up_shift_column(array, index, -n)


def rotate_bits(n: int) -> int:
    """
    Поворачивает на 180 градусов бинарное представление числа n и преобразует его обратно в целое число.
    :param n: Наше число, которое мы хотим развернуть по битам.
    Примеры есть в тестах.
    """
    # Преобразуем число n в бинарную строку (битовую строку)
    # Переворачиваем строку задом наперёд (выполняем реверс)
    # Преобразуем перевернутую строку обратно в целое число в двоичной системе
    return int(bin(n)[2:][::-1], 2)


