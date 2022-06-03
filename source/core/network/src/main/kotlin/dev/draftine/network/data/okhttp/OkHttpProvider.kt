package dev.draftine.network.data.okhttp

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val DEFAULT_TIMEOUT = 30L

class OkHttpProvider {

    fun provide(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}