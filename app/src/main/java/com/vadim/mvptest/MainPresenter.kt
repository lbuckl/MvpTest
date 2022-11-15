package com.vadim.mvptest

/**
 * Презенрёр связывает между собой модель и вью
 */
class MainPresenter(private val viewCallback: MainView) {
    private val model = CountersModel()

    fun counterClick(id: Int){
        when(id){
            BUTTON_ONE -> {
                val nextValue = model.next(BUTTON_ONE)
                viewCallback.setButtonText(BUTTON_ONE, nextValue.toString())
            }
            BUTTON_TWO -> {
                val nextValue = model.next(BUTTON_TWO)
                viewCallback.setButtonText(BUTTON_TWO, nextValue.toString())
            }
            BUTTON_THREE -> {
                val nextValue = model.next(BUTTON_THREE)
                viewCallback.setButtonText(BUTTON_THREE, nextValue.toString())
            }
        }
    }
}