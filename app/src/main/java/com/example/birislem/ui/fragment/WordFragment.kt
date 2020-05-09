package com.example.birislem.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.birislem.R
import com.example.birislem.bussines.word.AsyncOneWord
import com.example.birislem.bussines.word.OneWord
import com.example.birislem.bussines.word.abstr.OneWordListener
import com.example.birislem.dataAcces.FakeWordService
import kotlinx.android.synthetic.main.fragment_word.view.*

class WordFragment: Fragment(), OneWordListener {

    private lateinit var asyncOneWord: AsyncOneWord
    internal lateinit var pDialog: ProgressDialog
    private val charSource = "abcdefghijklmnoprstuvyz"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_word, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initprogressDialog()

        view.buttonFindWord.setOnClickListener {
            onButtonFindWordClick()
        }

        view.buttonRandom.setOnClickListener {
            onButtonRandomClick()
        }
    }

    private fun initprogressDialog() {
        pDialog = ProgressDialog(activity)
        pDialog.setTitle("Hesaplanıyor")
        pDialog.setMessage("Hesaplanıyor")
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        pDialog.setCancelable(false);
    }

    private fun onButtonRandomClick() {
        view!!.editTextNumber1.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber2.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber3.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber4.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber5.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber6.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber7.setText(charSource.toCharArray().random().toString())
        view!!.editTextNumber8.setText(charSource.toCharArray().random().toString())
    }

    private fun onButtonFindWordClick() {
        pDialog.show()
        var charArray = arrayOf<Char>(
            view!!.editTextNumber1.text.first(),
            view!!.editTextNumber2.text.first(),
            view!!.editTextNumber3.text.first(),
            view!!.editTextNumber4.text.first(),
            view!!.editTextNumber5.text.first(),
            view!!.editTextNumber6.text.first(),
            view!!.editTextNumber7.text.first(),
            view!!.editTextNumber8.text.first()
            )
        val wordService = FakeWordService()
        asyncOneWord = AsyncOneWord( OneWord(wordService.getWordList(context!!.resources), charArray, this) )
        asyncOneWord.execute()
    }

    override fun onWordsFound(foundWordList: List<String>) {
        var data = foundWordList.toMutableList().sortedByDescending { it.length }.toList()
        data = addScore(data)
        view!!.listView.adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            data
        )
        pDialog.dismiss()
    }

    private fun addScore(list: List<String>): List<String>{
        val mutableList = list.toMutableList()
        for ((index,item) in mutableList.withIndex()){
            val size = item.length
            var scoreText = " - skor : "
            when(size){
                9-> scoreText += "15"
                8-> scoreText += "11"
                7-> scoreText += "9"
                6-> scoreText += "7"
                5-> scoreText += "5"
                4-> scoreText += "4"
                3-> scoreText += "3"
            }
            mutableList[index] += scoreText
        }
        return mutableList.toList()
    }
}