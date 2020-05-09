package com.example.birislem.bussines.word

import android.os.AsyncTask

class AsyncOneWord(val oneTransaction: OneWord) :
    AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void?): Void? {
        oneTransaction.findWords()
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        oneTransaction.runAnswer()
    }
}