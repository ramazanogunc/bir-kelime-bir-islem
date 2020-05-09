package com.example.birislem.bussines.transaciton

import com.example.birislem.util.exceptions.ItemTransactionException
import com.example.birislem.model.MathSigns
import com.example.birislem.ui.fragment.TransactionFragment


class OneTransaction(
    private val numbers: IntArray,
    private val goalNumber: Int,
    val oneTransactionListener: TransactionFragment
) {
    private val transactionList = mutableListOf<ItemOneTransaction>()
    private lateinit var closeResult: List<ItemOneTransaction>

    private var numberClone = numbers.toMutableList()
    private var success = false

    fun calculate() {
        var i = 0
        for (i in i..1000000) {
            success = calculateBase()
            if (success)
                break
        }
    }

    fun runAnswer(){
        if (success)
            oneTransactionListener.onSuccessTransaction(transactionList)
        else
            if(this::closeResult.isInitialized){
                oneTransactionListener.onFailTransaction(closeResult)
            }
    }

    private fun calculateBase(): Boolean {
        for (i in 0..numberClone.size) {
            try {
                if (i == 5 ){
                    break
                }
                val item =
                    ItemOneTransaction(
                        numberClone[i],
                        getMathSign(),
                        numberClone[i + 1]
                    )
                transactionList.add(item)
                numberClone[i + 1] = item.result
                if (checkResult())
                    return true

            } catch (e: ItemTransactionException) {
                break
            } catch (e : Exception){
                break
            }
        }
        mixArray()
        transactionList.clear()

        return false
    }

    private fun checkResult(): Boolean {
        // goal number success
        val currentMajority = (transactionList.last().result - goalNumber)
        if (currentMajority == 0) {
            return true
        }

        // goal number is not success
        var bestMajorityStart = -10
        var bestMajorityEnd = 10

        if(this::closeResult.isInitialized){
            if ((closeResult.last().result - goalNumber) > 0){
                bestMajorityStart = -1*(closeResult.last().result - goalNumber)
                bestMajorityEnd = (closeResult.last().result - goalNumber)
            }else{
                bestMajorityStart = (closeResult.last().result - goalNumber)
                bestMajorityEnd = -1*(closeResult.last().result - goalNumber)
            }
        }

         if (currentMajority in bestMajorityStart until bestMajorityEnd) {
            closeResult = transactionList.toList()

        }
        return false
    }

    private fun getMathSign(): MathSigns {
        return MathSigns.values().random()
    }

    private fun mixArray() {
        var temp = numbers.toMutableList()
        temp.shuffle()
        numberClone = temp
    }
}