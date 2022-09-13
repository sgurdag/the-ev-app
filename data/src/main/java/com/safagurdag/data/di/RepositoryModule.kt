package com.safagurdag.data.di

import com.safagurdag.data.repository.TheEVRepository
import com.safagurdag.domain.repository.ITheEVRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideTheEVRepository(repository: TheEVRepository): ITheEVRepository
}