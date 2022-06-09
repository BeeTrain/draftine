package dev.draftine.imagetape.data.api

import com.squareup.moshi.Moshi
import dev.draftine.imagetape.data.model.ImageTapeResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val IMAGES_BASE_URL = "https://picsum.photos/"
private const val API_VERSION = "v2"

interface ImageTapeService {

    @GET("$API_VERSION/list?limit=15")
    suspend fun getImages(
        @Query("page") page: Int
    ): List<ImageTapeResponse>

    class Provider(
        private val okHttpClient: OkHttpClient,
        private val moshi: Moshi
    ) {

        fun provide(): ImageTapeService {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(IMAGES_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create()
        }
    }
}