package ru.pauliesoft.test.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pauliesoft.test.data.NetworkRepository
import ru.pauliesoft.test.data.NetworkRepositoryContract

@Module
@InstallIn(SingletonComponent::class)
interface BindsRepositoriesModule {

    @Binds
    fun bindNetworkRepository(networkRepository: NetworkRepository): NetworkRepositoryContract

}