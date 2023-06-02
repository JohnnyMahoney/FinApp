package com.example.finapp.ui.theme.thirdPage

import androidx.lifecycle.ViewModel
import com.example.finapp.data.model.Transaction
import com.example.finapp.data.repository.TransactionsRepository
import com.google.type.DateTime

class BlankViewModel3(private val repository: TransactionsRepository) : ViewModel() {

    var currentTransaction: Transaction = Transaction();
    fun addTransaction() {
        currentTransaction.time = System.currentTimeMillis()
        repository.addTransaction(currentTransaction);
        currentTransaction = Transaction();
    }

    fun addNumber(number: String) {
        currentTransaction.value += number
    }
    fun clearLastDigit() {
        currentTransaction.value = currentTransaction.value.dropLast(1)
    }
}