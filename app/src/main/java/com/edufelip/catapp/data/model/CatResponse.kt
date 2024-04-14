package com.edufelip.catapp.data.model

import com.edufelip.catapp.domain.model.Cat
import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("tags")
    val tags: List<String>? = null
) {
    fun toDomain(): Cat {
        return with(this) {
            Cat(
                id = this.id,
                name = this.name,
                tags = this.tags
            )
        }
    }
}