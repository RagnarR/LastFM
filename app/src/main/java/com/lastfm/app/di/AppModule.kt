package com.lastfm.app.di

import com.lastfm.app.config.ConfigProvider
import com.lastfm.app.config.ConfigProviderImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Reusable
    fun provideConfigProvider(): ConfigProvider = ConfigProviderImpl
}