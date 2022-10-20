package ru.pauliesoft.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.pauliesoft.test.Const.BASE_URL
import ru.pauliesoft.test.data.RetrofitAPI
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class RestAPIModule {

    companion object {
        private const val NETWORK_CONNECT_TIMEOUT: Long = 5
        private const val NETWORK_READ_TIMEOUT: Long = 5
        private const val NETWORK_CALL_TIMEOUT: Long = 5
    }

    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            callTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(false)
        }.build()
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRetrofitAPI(
        retrofit: Retrofit
    ): RetrofitAPI {
        return retrofit.create(RetrofitAPI::class.java)
    }
}