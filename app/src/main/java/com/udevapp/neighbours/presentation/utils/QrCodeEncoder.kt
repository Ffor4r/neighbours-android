package com.udevapp.neighbours.presentation.utils

import android.graphics.Bitmap
import androidx.core.content.ContextCompat.getSystemService
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder


class BitmapEncoder {
    companion object {
        fun encode(data: String, width: Int, height: Int): Bitmap? {
            return try {
                BarcodeEncoder().createBitmap(
                    MultiFormatWriter().encode(
                        data,
                        BarcodeFormat.QR_CODE,
                        width,
                        height
                    )
                )
            } catch (e: WriterException) {
                null
            }
        }
    }
}