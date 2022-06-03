package dev.draftine.network.data.moshi

import com.squareup.moshi.Moshi

class MoshiProvider {

    fun provide(): Moshi {
        return Moshi.Builder()
            .build()
    }
}