package com.example.oblig1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class ConverterHelper {

    /**
     * Converts bitmap to byte-array.
     * Used when storing an image in the database.
     * @param bitmap
     * @return byte[]
     */
    @TypeConverter
    public byte[] BitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return outputStream.toByteArray();
    }

    /**
     * Converts byte-array to bitmap. Used when retrieving an image from the database.
     * @param byteArray
     * @return
     */
    public Bitmap ByteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
