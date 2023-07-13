package com.example.finapp.ui.theme.secondPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finapp.R
import com.example.finapp.data.model.TransactionType
import com.example.finapp.data.repository.TransactionsRepository


class BlankFragment2 : Fragment() {
    companion object {
        fun newInstance() = BlankFragment2()
    }

    private lateinit var viewModel: BlankViewModel2
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = TransactionModelFactory(TransactionsRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[BlankViewModel2::class.java]
        viewModel.getTransactions()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireView().findViewById(R.id.transactionsList)

        val llm = LinearLayoutManager(this.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm

        viewModel.dataList.observe(viewLifecycleOwner, Observer { dataList ->
            Log.d("BlankFragment2", "DataList changed, size: ${dataList.size}")
            val adapter = TransactionAdapter(dataList)
            recyclerView.adapter = adapter
            // Calculate the total sum of transactions
            var totalSum = 0.0
            dataList.forEach { transaction ->
                // Convert the value to a Double and add it to the total
                try {
                    val value = transaction.value.toDouble()

                    // Assuming you have income and outcome types as a property in Transaction model
                    if (transaction.type == TransactionType.INCOME) {
                        totalSum += value
                    } else if (transaction.type == TransactionType.OUTCOME) {
                        totalSum -= value
                    }
                } catch (e: NumberFormatException) {
                    // This block will be executed if the String cannot be converted to a Double
                    Log.e("BlankFragment2", "Error converting transaction value to double", e)
                }
            }
            Log.d("BlankFragment2", "Total sum calculated: $totalSum")

            // Set total sum to the TextView
            // Make sure you have a TextView with the id totalSumTextView in your layout
            val totalSumTextView = requireView().findViewById<TextView>(R.id.totalSumTextView)
            activity?.runOnUiThread {
                totalSumTextView.text = "Total left: $totalSum Euro"
                Log.d("BlankFragment2", "Total sum set to TextView")
            }
        })
        viewModel.getTransactions()
    }
}