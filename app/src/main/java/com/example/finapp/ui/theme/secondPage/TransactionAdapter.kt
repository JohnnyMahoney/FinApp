package com.example.finapp.ui.theme.secondPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finapp.R
import com.example.finapp.data.model.Transaction

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        // Add more TextViews for other properties of the Transaction object
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = transactions[position]

        holder.valueTextView.text = transaction.value
        holder.typeTextView.text = transaction.type.toString()
        // Set values for other TextViews using transaction object properties
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}
