package dev.draftine.advices.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdviceResponse(
    @Json(name = "slip")
    val slip: SlipResponse
)