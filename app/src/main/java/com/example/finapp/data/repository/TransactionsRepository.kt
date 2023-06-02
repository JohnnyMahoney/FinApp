package com.example.finapp.data.repository

import android.util.Log
import com.example.finapp.data.model.Transaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TransactionsRepository {

    fun addTransaction(transaction: Transaction) {
        val db = Firebase.firestore

        db.collection("transactions")
            .add(transaction.toMap())
            .addOnSuccessListener { documentReference ->
                Log.d(">>>>>", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(">>>>>", "Error adding document", e)
            }
    }
}