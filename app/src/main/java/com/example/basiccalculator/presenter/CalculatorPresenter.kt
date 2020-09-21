package com.example.basiccalculator.presenter

import com.example.basiccalculator.model.CalculatorModel
import com.example.basiccalculator.utils.FormatUtil
import com.example.basiccalculator.view.ICalculatorView


class CalculatorPresenter(private var view: ICalculatorView) : ICalculatorPresenter {
    private var calculatorModel = CalculatorModel("", "", "")
    private var onError: Boolean = false
    private var lastNumeric: Boolean = false
    private var lastEquals: Boolean = false

    override fun getResult() {
    }

    override fun numberClick(number: String) {
        if (lastNumeric) {
            calculatorModel.secondNum = calculatorModel.firstNum
            calculatorModel.firstNum = ""
        } else if (lastEquals) {
            resetCalculator()
        }

        calculatorModel.firstNum += number
        lastEquals = false
        lastNumeric = false
        onError = false
        updateDisplay()
    }

    override fun operationClick(operation: String) {
        if (!onError) {
            if (calculatorModel.operation != "" && !lastNumeric) {
                doCalculations()
                if (onError) return
            }
        }
        calculatorModel.operation = operation
        lastNumeric = true
        updateDisplay()
    }

    override fun clearClick() {
        resetCalculator()
        updateDisplay()
    }

    override fun equalClick() {
        if (!onError) {
            if (calculatorModel.operation != "" && calculatorModel.firstNum != "" && calculatorModel.secondNum != "") {
                doCalculations()
                if (onError) return
            }
        }
        updateDisplay()
    }

    private fun doCalculations() {
        var res = 0.0
        when (calculatorModel.operation) {
            "+" -> res = calculatorModel.secondNum.toDouble() + calculatorModel.firstNum.toDouble()
            "-" -> res = calculatorModel.secondNum.toDouble() - calculatorModel.firstNum.toDouble()
            "×" -> res = calculatorModel.secondNum.toDouble() * calculatorModel.firstNum.toDouble()
            "÷" -> if (calculatorModel.firstNum != "0") {
                res = calculatorModel.secondNum.toDouble() / calculatorModel.firstNum.toDouble()
            }
        }
        if (res == 0.0) {
            switchToErrorState()
        } else {
            calculatorModel.firstNum = FormatUtil.formatResult(res)
        }
        calculatorModel.operation = ""
        calculatorModel.secondNum = ""
        lastEquals = true
        updateDisplay()
    }

    private fun switchToErrorState() {
        calculatorModel.firstNum = "Error"
        onError = true

    }

    private fun updateDisplay() {
        view.setValue(calculatorModel.firstNum)
    }

    private fun resetCalculator() {
        calculatorModel.operation = ""
        calculatorModel.firstNum = ""
        calculatorModel.secondNum = ""

        lastEquals = false
        lastNumeric = false
        onError = false
    }
}