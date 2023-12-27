package com.sarabyeet.datastoretest

import android.content.Context
import com.sarabyeet.settings.SettingsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {
    @Provides
    @Singleton
    fun provideSettings(@ApplicationContext context: Context): SettingsHelper {
        return SettingsHelper(context)
    }
}