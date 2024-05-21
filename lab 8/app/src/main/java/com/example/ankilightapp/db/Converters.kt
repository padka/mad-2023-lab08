package com.example.ankilightapp.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray? {
        return if (bitmap == null) {
            null
        } else {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.toByteArray()
        }
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray?): Bitmap? {
        return if (byteArray == null) {
            null
        } else {
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }
    }
}
