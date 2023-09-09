package com.keepme.custompaginationjp.di

import com.keepme.custompaginationjp.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        val loggingBodyLevel = HttpLoggingInterceptor.Level.BODY
        val loggingNoneLevel = HttpLoggingInterceptor.Level.NONE
        loggingInterceptor.level = loggingBodyLevel
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }

    @Provides
    fun providerApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://testpos.pythonanywhere.com/api/v1/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()


}
