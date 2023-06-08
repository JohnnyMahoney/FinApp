package com.example.finapp.ui.theme.secondPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finapp.R
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
            val adapter = TransactionAdapter(dataList)
            recyclerView.adapter = adapter
        })
        viewModel.getTransactions()
    }
}