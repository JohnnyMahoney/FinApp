package com.example.finapp.ui.theme.secondPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finapp.R

class BlankFragment2 : Fragment() {

    companion object {
        fun newInstance() = BlankFragment2()
    }

    private lateinit var viewModel: BlankViewModel2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BlankViewModel2::class.java)
        // TODO: Use the ViewModel
    }


}