package com.jess.kurly.app.di

import com.jess.kurly.app.navigotor.NavigatorImpl
import com.jess.kurly.navigator.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigatorModule {

    @Singleton
    @Provides
    fun provideNavigator(): Navigator {
        return NavigatorImpl()
    }
}
