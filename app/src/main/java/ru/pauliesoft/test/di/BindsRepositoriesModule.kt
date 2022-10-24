package ru.pauliesoft.test.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.pauliesoft.test.data.CoordinatesRepository
import ru.pauliesoft.test.data.FilesRepository
import ru.pauliesoft.test.domain.CoordinatesRepositoryContract
import ru.pauliesoft.test.domain.FilesRepositoryContract

@Module
@InstallIn(SingletonComponent::class)
interface BindsRepositoriesModule {

    @Binds
    fun bindCoordinatesRepository(coordinatesRepository: CoordinatesRepository): CoordinatesRepositoryContract

    @Binds
    fun bindFilesRepository(filesRepository: FilesRepository): FilesRepositoryContract

}