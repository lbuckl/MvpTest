package com.vadim.mvptest

import android.view.View

//Переменные определяющие соотвествие ViewID b id хранения в модели
const val VIEW_ID_ERROR = -1
const val BUTTON_ONE = 0
const val BUTTON_TWO = 1
const val BUTTON_THREE = 2

/**
 * Функция пререводящая ViewId в id хранения в модели
 */
fun getNumericViewId(view: View):Int{
    return when(view.id){
        R.id.btn_counter1 -> BUTTON_ONE
        R.id.btn_counter2 -> BUTTON_TWO
        R.id.btn_counter3 -> BUTTON_THREE
        else -> {VIEW_ID_ERROR}
    }
}