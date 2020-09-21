package com.example.basiccalculator.utils

import java.text.DecimalFormat
import java.text.NumberFormat

class FormatUtil {
    companion object {
        fun formatResult(amount: Double): String {
            val nf: NumberFormat = DecimalFormat("#.######")
            return nf.format(amount)
        }
    }
}