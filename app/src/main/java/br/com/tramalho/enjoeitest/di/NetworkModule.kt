package br.com.tramalho.enjoeitest.di

import br.com.tramalho.enjoeitest.BuildConfig
import br.com.tramalho.enjoeitest.infraestructure.ServiceApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val TIMEOUT: Long = 5

class NetworkModule {


    fun retrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(httpClient)
                .build()
    }


    fun logInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient
                .Builder()
                .addInterceptor(logging)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    fun serviceAPI(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
}