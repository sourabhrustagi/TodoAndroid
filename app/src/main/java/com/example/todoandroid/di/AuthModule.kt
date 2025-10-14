package com.example.todoandroid.di

import com.example.todoandroid.config.AppConfig
import com.example.todoandroid.data.api.service.AuthApiService
import com.example.todoandroid.data.api.service.MockAuthService
import com.example.todoandroid.data.repository.AuthRepository
import com.example.todoandroid.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    companion object{
        @Provides
        @Singleton
        fun provideOkHttpClient(
            appConfig: AppConfig
        ): OkHttpClient{
            val builder = OkHttpClient.Builder()
                .connectTimeout(appConfig.apiTimeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(appConfig.apiTimeoutSeconds, TimeUnit.SECONDS)
                .writeTimeout(appConfig.apiTimeoutSeconds, TimeUnit.SECONDS)
            if (appConfig.useMockInterceptor){
                builder.addInterceptor (MockLoginInterceptor())
            }
        }
    }
    @Binds
    @Singleton
    abstract fun bindAuthApiService(
        mockAuthService: MockAuthService
    ): AuthApiService

}