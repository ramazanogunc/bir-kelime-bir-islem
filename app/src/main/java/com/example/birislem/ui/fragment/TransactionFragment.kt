package com.example.birislem.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.birislem.bussines.transaciton.AsyncOneTransaction
import com.example.birislem.bussines.transaciton.OneTransaction
import com.example.birislem.R
import com.example.birislem.bussines.transaciton.ItemOneTransaction
import com.example.birislem.bussines.transaciton.abstr.OneTransactionListener
import com.example.birislem.util.toast
import kotlinx.android.synthetic.main.fragment_transaction.view.*
import java.lang.Exception

class TransactionFragment : Fragment(), OneTransactionListener {

    private lateinit var oneTransaction: OneTransaction
    internal lateinit var pDialog: ProgressDialog
    private lateinit var fView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fView = view
        initprogressDialog()
        view.buttonCalculate.setOnClickListener {
            onButtonCalculateClick()
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


    fun onButtonRandomClick() {
        view?.editTextNumber1?.setText((0..9).random().toString())
        view?.editTextNumber2?.setText((0..9).random().toString())
        view?.editTextNumber3?.setText((0..9).random().toString())
        view?.editTextNumber4?.setText((0..9).random().toString())
        view?.editTextNumber5?.setText((0..9).random().toString())
        view?.editTextNumberTwoDigit?.setText(((1..9).random() * 10).toString())
        view?.editTextNumberGoal?.setText((100..999).random().toString())
    }

    fun onButtonCalculateClick() {
        try {
            pDialog.show()
            fView.editTextNumber1.text.toString().toInt()
            val numbers = intArrayOf(
                fView.editTextNumber1.text.toString().toInt(),
                fView.editTextNumber2.text.toString().toInt(),
                fView.editTextNumber3.text.toString().toInt(),
                fView.editTextNumber4.text.toString().toInt(),
                fView.editTextNumber5.text.toString().toInt(),
                fView.editTextNumberTwoDigit.text.toString().toInt()
            )
            oneTransaction =
                OneTransaction(
                    numbers,
                    fView.editTextNumberGoal.text.toString().toInt(),
                    this
                )
            AsyncOneTransaction(
                oneTransaction
            ).execute()
        } catch (e: Exception) {
            activity?.toast(e.message.toString())
        }
    }


    override fun onSuccessTransaction(transactionList: List<ItemOneTransaction>) {
        activity?.toast("sonuç bulundu")
        view!!.listView.adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            transactionList
        )
        pDialog.dismiss()
    }

    override fun onFailTransaction(closeResult: List<ItemOneTransaction>) {
        activity?.toast("En yakın sonuç bulundu")
        view!!.listView.adapter = ArrayAdapter(
            activity!!,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            closeResult
        )
        pDialog.dismiss()
    }
}