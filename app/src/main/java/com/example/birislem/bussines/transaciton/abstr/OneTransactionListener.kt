package com.example.birislem.bussines.transaciton.abstr

import com.example.birislem.bussines.transaciton.ItemOneTransaction

interface OneTransactionListener {
    fun onSuccessTransaction(transactionList: List<ItemOneTransaction>)
    fun onFailTransaction(closeResult: List<ItemOneTransaction>);
}