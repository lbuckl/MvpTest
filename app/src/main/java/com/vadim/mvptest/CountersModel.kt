package com.vadim.mvptest

/**
 * Модель для предоставления данных по счётчикам
 */
class CountersModel {
    /**
     * Хранилище значений счётчиков
     * при расширении, необходимо добавить переменные и логику в функцию [getNumericViewId]
     */
    private val counters = mutableListOf(0, 0, 0)

    //получить текушие значения
    private fun getCurrent(index: Int): Int {
        return counters[index]
    }

    //Добавить 1 к текущему значению
    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

    //TODO не понятно зачем???
    fun set(index: Int, value: Int){
        counters[index] = value
    }
}