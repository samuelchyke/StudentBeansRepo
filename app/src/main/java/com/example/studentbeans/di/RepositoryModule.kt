package com.example.studentbeans.di

import com.example.studentbeans.repository.NetworkRepository
import com.example.studentbeans.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

}