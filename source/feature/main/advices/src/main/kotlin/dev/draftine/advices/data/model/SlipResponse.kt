package dev.draftine.advices.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SlipResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "advice")
    val advice: String
)