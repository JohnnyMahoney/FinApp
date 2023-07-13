package com.example.finapp.ui.theme.thirdPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finapp.R
import com.example.finapp.data.model.TransactionType
import com.example.finapp.data.repository.TransactionsRepository


class BlankFragment3 : Fragment(), BottomSheetCallback {
    companion object {
        fun newInstance() = BlankFragment3()
    }

    private lateinit var viewModel: BlankViewModel3
    private val bottomSheetFragment = BottomSheetFragment()

    override fun onSaveCliecked() {
        viewModel.addTransaction()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank3, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = TransactionModelFactory(TransactionsRepository())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[BlankViewModel3::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.currentTransaction.observe(viewLifecycleOwner, Observer { transaction ->
//            textView.text = transaction.value // assuming `value` is a string.
        })
        super.onViewCreated(view, savedInstanceState)
        val textView = requireView().findViewById<View>(R.id.variableEditText) as TextView
        val applyButton = requireView().findViewById<View>(R.id.applyButton) as Button
        val clearButton = requireView().findViewById<View>(R.id.clearButton) as Button
        val tableLayout: TableLayout = requireView().findViewById(R.id.buttons_view)
        val radioGroup: RadioGroup = requireView().findViewById(R.id.radioGroup)
        val incomeRadioButton: RadioButton = requireView().findViewById(R.id.incomeRadioButton)
        val outcomeRadioButton: RadioButton = requireView().findViewById(R.id.outcomeRadioButton)

        // Observe the LiveData of the currentTransaction object
        viewModel.currentTransaction.observe(viewLifecycleOwner, Observer { transaction ->
            // Update the textView when the currentTransaction is updated
            if (transaction != null) {
                textView.text = transaction.value
            }
        })

        for (i in 0 until tableLayout.childCount) {
            val row = tableLayout.getChildAt(i) as TableRow

            for (j in 0 until row.childCount) {
                val button = row.getChildAt(j) as Button

                button.setOnClickListener {
                    when {
                        button.id == R.id.clearButton -> {
                            radioGroup.clearCheck()
                        }

                        button.id == R.id.clear -> {
                            viewModel.clearLastDigit()
                        }

                        button.id == R.id.point -> {
                            viewModel.addNumber(button.text.toString())
                        }

                        else -> {
                            viewModel.addNumber(button.text.toString())
                            val type =
                                if (incomeRadioButton.isChecked) TransactionType.INCOME else TransactionType.OUTCOME
                            viewModel.addType(type)
                        }
                    }
                }
            }
        }

        applyButton.setOnClickListener {
            bottomSheetFragment.callback = this
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }
}