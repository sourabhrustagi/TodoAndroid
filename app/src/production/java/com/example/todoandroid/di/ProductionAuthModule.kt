package com.example.todoandroid.di

import com.example.todoandroid.config.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductionAuthModule {
    @Provides
    @Singleton
    fun provideDevelopmentOkHttpClient(
        appConfig: AppConfig
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(appConfig.apiTimeoutSeconds, TimeUnit.SECONDS)
            .readTimeout(appConfig.apiTimeoutSeconds, TimeUnit.SECONDS)
            .writeTimeout(appConfig.apiTimeoutSeconds, TimeUnit.SECONDS)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideDevelopmentRetrofit(
        okHttpConfig: OkHttpClient,
        appConfig: AppConfig
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(appConfig.baseUrl)
            .client(okHttpConfig)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
    }
}