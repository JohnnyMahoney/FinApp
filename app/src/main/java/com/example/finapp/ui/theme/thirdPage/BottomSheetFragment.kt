package com.example.finapp.ui.theme.thirdPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.finapp.R
import com.example.finapp.data.model.Category
import com.example.finapp.data.repository.TransactionsRepository
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment: BottomSheetDialogFragment() {

    var callback: BottomSheetCallback? = null
    private lateinit var viewModel: BlankViewModel3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_fragment,container,false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = TransactionModelFactory(TransactionsRepository())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[BlankViewModel3::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.currentTransaction.observe(viewLifecycleOwner, Observer { transaction ->
            // Update UI based on transaction
        })

        val saveButton = view.findViewById<Button>(R.id.saveButton)
        saveButton.setOnClickListener {
            callback?.onSaveCliecked()
            dismiss()
        }

        val myButton1: ImageButton = view.findViewById(R.id.btn1)
        val myButton2: ImageButton = view.findViewById(R.id.btn2)
        val myButton3: ImageButton = view.findViewById(R.id.btn3)
        val myButton4: ImageButton = view.findViewById(R.id.btn4)


        myButton1.setOnClickListener{
            val category1 = Category.sport
            viewModel.addCategory(category1)
            Toast.makeText(context,"Sport selected",Toast.LENGTH_SHORT).show()
        }
        myButton2.setOnClickListener{
            val category2 = Category.food
            viewModel.addCategory(category2)
            Toast.makeText(context,"Food selected",Toast.LENGTH_SHORT).show()
        }
        myButton3.setOnClickListener{
            val category3= Category.auto
            viewModel.addCategory(category3)
            Toast.makeText(context,"Car selected",Toast.LENGTH_SHORT).show()
        }
        myButton4.setOnClickListener{
            val category4 = Category.house
            viewModel.addCategory(category4)
            Toast.makeText(context,"House selected",Toast.LENGTH_SHORT).show()
        }
    }
}