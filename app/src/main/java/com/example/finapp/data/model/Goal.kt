package com.example.finapp.data.model

class Goal {
    var id: String = ""
    var name: String = ""
    var targetAmount: Double = 0.0
    var currentAmount: Double = 0.0

    constructor()
    constructor(
        id: String,
        name: String,
        targetAmount: Double,
        currentAmount: Double
    ) {
        this.id = id
        this.name = name
        this.targetAmount = targetAmount
        this.currentAmount = currentAmount
    }

    fun toMap(): Map<String, Any> {
        val goalMap = mutableMapOf<String, Any>()
        goalMap["id"] = id
        goalMap["name"] = name
        goalMap["targetAmount"] = targetAmount
        goalMap["currentAmount"] = currentAmount
        return goalMap
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Goal {
            val goal = Goal()
            if (map.containsKey("id"))
                goal.id = map["id"] as String
            if (map.containsKey("name"))
                goal.name = map["name"] as String
            if (map.containsKey("targetAmount"))
                goal.targetAmount = map["targetAmount"] as Double
            if (map.containsKey("currentAmount"))
                goal.currentAmount = map["currentAmount"] as Double
            return goal
        }
    }
}
