package com.velocityappsdj.projektdawn.model

import androidx.databinding.Bindable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Project(
    var id: String? = null,
    var name: String? = null,
    var summary: String? = null,
    var itemTypes: List<ItemType>? = null
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "summary" to summary,
            "itemTypes" to itemTypes
        )
    }
}
