package com.jess.kurly.feature.home.presentation.di

import com.jess.kurly.data.repository.KurlyRepositoryImpl
import com.jess.kurly.domain.repository.KurlyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelComponentModule {

    @Binds
    abstract fun bindKurlyRepository(
        repository: KurlyRepositoryImpl,
    ): KurlyRepository
}
