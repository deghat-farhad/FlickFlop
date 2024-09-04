package farhad.deghat.flickflop.common.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import farhad.deghat.flickflop.common.data.utils.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

const val BASE_URL_NAME = "baseUrl"
const val IMAGE_BASE_URL_NAME = "imageBaseUrl"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun retrofitNoAuth(
        httpClient: OkHttpClient,
        jsonConverterFactory: Converter.Factory,
        resultCallAdapterFactory: ResultCallAdapterFactory,
        @Named(BASE_URL_NAME) baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(jsonConverterFactory)
            .addCallAdapterFactory(resultCallAdapterFactory)
            .client(httpClient)
            .build()
    }

    @Provides
    fun jsonConverterFactory(
        json: Json
    ): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    fun json(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Named(BASE_URL_NAME)
    fun baseUrl() = "https://api.themoviedb.org/3/"

    @Provides
    fun httpClientNoAuthentication(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun interceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("User-Agent", "FlickFlop")
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Connection", "keep-alive")
                .build()
            chain.proceed(request)
        }
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    @Provides
    @Named(IMAGE_BASE_URL_NAME)
    fun imageBaseUrl() = "https://image.tmdb.org/t/p/w780"
}