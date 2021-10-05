package com.kabos.firebasesample.di

import com.kabos.firebasesample.model.Constants.Companion.BASE_URL
import com.kabos.firebasesample.repository.MyRepository
import com.kabos.firebasesample.repository.NotificationAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideNotificationAPI(): NotificationAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(NotificationAPI::class.java)

    @Singleton
    @Provides
    fun provideRepository(service: NotificationAPI) =
        MyRepository(service)
}
