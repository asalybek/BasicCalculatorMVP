package com.example.basiccalculator.presenter

interface ICalculatorPresenter {
    fun getResult()
    fun numberClick(number: String)
    fun operationClick(operation: String)
    fun clearClick()
    fun equalClick()
}