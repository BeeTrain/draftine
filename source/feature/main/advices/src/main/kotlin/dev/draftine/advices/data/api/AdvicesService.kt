package dev.draftine.advices.data.api

import com.squareup.moshi.Moshi
import dev.draftine.advices.data.model.AdviceResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val ADVICES_BASE_URL = "https://api.adviceslip.com/"

interface AdvicesService {

    @GET("advice")
    suspend fun getAdvice(): AdviceResponse

    class Provider(
        private val okHttpClient: OkHttpClient,
        private val moshi: Moshi
    ) {

        fun provide(): AdvicesService {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ADVICES_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
        }
    }
}