package com.example.birislem.bussines.transaciton

import com.example.birislem.util.exceptions.ItemTransactionException
import com.example.birislem.model.MathSigns

class ItemOneTransaction(
    private val first: Int,
    private val mathSign: MathSigns,
    private val second: Int
)
{
    var result: Int = 0

    init {
        calculate();
    }

    private fun calculate(){
        when(mathSign){
            MathSigns.NONE -> result = first
            MathSigns.SUM -> result = first + second
            MathSigns.EXTRACTION -> result = first - second
            MathSigns.IMPACT -> result = first * second
            MathSigns.DIVISION -> result = first / second
        }
        validateCalculate();
    }

    private fun validateCalculate(){
        if ( result <= 0){
            throw ItemTransactionException();
        }

        if (mathSign == MathSigns.DIVISION && first % second != 0){
            throw ItemTransactionException();
        }
    }

    override fun toString(): String {
        var sign = " none ";
        when(mathSign){
            MathSigns.SUM -> sign = " + "
            MathSigns.EXTRACTION -> sign = " - "
            MathSigns.IMPACT -> sign = " x "
            MathSigns.DIVISION -> sign = " / "
        }
        return "$first $sign $second = $result"
    }
}