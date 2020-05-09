package com.example.birislem.bussines.transaciton

import android.os.AsyncTask

class AsyncOneTransaction(val oneTransaction: OneTransaction) :
    AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        oneTransaction.calculate()
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        oneTransaction.runAnswer()
    }
}