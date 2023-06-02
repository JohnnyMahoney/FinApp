package com.example.finapp.data.model

enum class TransactionType {
    income,
    outcome
}

enum class Category {
    auto,
    food,
    sport,
    house
}


class Transaction {
    var value: String = ""
    var type: TransactionType = TransactionType.income
    var category: Category = Category.auto
    var time: Long = 0

    constructor()

    constructor(
        value: String,
        type: TransactionType,
        category: Category,
        time: Long
    ) {
        this.value = value
        this.type = type
        this.category = category
        this.time = time
    }

    fun toMap(): Map<String, Any> {
        val transactionMap = mutableMapOf<String, Any>()
        transactionMap["value"] = value
        transactionMap["type"] = type
        transactionMap["category"] = category
        transactionMap["time"] = time
        return transactionMap
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Transaction {
            val transaction = Transaction()
            if (map.containsKey("value"))
                transaction.value = map["value"] as String
            if (map.containsKey("type"))
                transaction.type = map["type"] as TransactionType
            if (map.containsKey("category"))
                transaction.category = map["category"] as Category
            if (map.containsKey("time"))
                transaction.time = map["time"] as Long

            return transaction
        }
    }
}

