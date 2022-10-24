package ru.pauliesoft.test.domain

import android.graphics.Bitmap

interface FilesRepositoryContract {

    suspend fun saveImage(bitmap: Bitmap): Boolean

}