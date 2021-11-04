package com.example.gb_mvp

private const val BUTTON_COUNTER_1: Int = 0
private const val BUTTON_COUNTER_2: Int = 1
private const val BUTTON_COUNTER_3: Int = 2

class MainPresenter(
    private val view: MainView,
    private val model: CountersModel = CountersModel()
) {
    fun buttonIncrement1() {
        val nextValue = model.next(BUTTON_COUNTER_1)
        view.setButtonValue1(nextValue.toString())
    }

    fun buttonIncrement2() {
        val nextValue = model.next(BUTTON_COUNTER_2)
        view.setButtonValue2(nextValue.toString())
    }

    fun buttonIncrement3() {
        val nextValue = model.next(BUTTON_COUNTER_3)
        view.setButtonValue3(nextValue.toString())
    }
}