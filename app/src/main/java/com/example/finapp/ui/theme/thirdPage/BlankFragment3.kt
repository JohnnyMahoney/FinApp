package com.example.finapp.ui.theme.thirdPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finapp.R
import com.example.finapp.data.repository.TransactionModelFactory
import com.example.finapp.data.repository.TransactionsRepository


class BlankFragment3 : Fragment() {
    companion object {
        fun newInstance() = BlankFragment3()
    }

    private lateinit var viewModel: BlankViewModel3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = TransactionModelFactory(TransactionsRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[BlankViewModel3::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView =
            requireView().findViewById<View>(R.id.variableEditText) as TextView
        val saveButton =
            requireView().findViewById<View>(R.id.saveButton) as Button

        val tableLayout: TableLayout = requireView().findViewById(R.id.buttons_view)

        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i) as TableRow

            for (j in 0 until row.childCount) {
                val button = row.getChildAt(j) as Button

                button.setOnClickListener {
                    when {
                        button.id == R.id.clear -> {
                            viewModel.clearLastDigit()
                            textView.text = viewModel.currentTransaction.value
                        }
                        button.id == R.id.point -> {
                            viewModel.addNumber(button.text.toString())
                            textView.text = viewModel.currentTransaction.value
                        }
                        else -> {
                            viewModel.addNumber(button.text.toString())
                            textView.text = viewModel.currentTransaction.value
                        }
                    }
                }
            }
        }

        textView.text = viewModel.currentTransaction.value
        saveButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                viewModel.addTransaction()
            }
        })

    }

}