package ru.pauliesoft.test.domain

import android.graphics.Bitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveFileInteractor @Inject constructor(
    private val filesRepository: FilesRepositoryContract
) {

    suspend fun saveFile(bitmap: Bitmap): Boolean {
        return withContext(Dispatchers.IO) {
            filesRepository.saveImage(bitmap)
        }
    }
}