package dev.draftine.ui.utils.coil

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import androidx.core.graphics.createBitmap
import dev.draftine.ui.extension.safeConfig

internal object CenterCropApplier {

    private val DEFAULT_PAINT = Paint(Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)

    fun apply(inBitmap: Bitmap, width: Int, height: Int): Bitmap {
        if (inBitmap.width == width && inBitmap.height == height) {
            return inBitmap
        }
        val scale: Float
        val dx: Float
        val dy: Float
        val matrix = Matrix()
        if (inBitmap.width * height > width * inBitmap.height) {
            scale = height.toFloat() / inBitmap.height.toFloat()
            dx = (width - inBitmap.width * scale) * 0.5f
            dy = 0f
        } else {
            scale = width.toFloat() / inBitmap.width.toFloat()
            dx = 0f
            dy = (height - inBitmap.height * scale) * 0.5f
        }

        matrix.setScale(scale, scale)
        matrix.postTranslate((dx + 0.5f).toInt().toFloat(), (dy + 0.5f).toInt().toFloat())

        val result = createBitmap(width, height, inBitmap.safeConfig)
        setAlpha(inBitmap, result)

        applyMatrix(inBitmap, result, matrix)
        return result
    }

    private fun setAlpha(inBitmap: Bitmap, outBitmap: Bitmap) {
        outBitmap.setHasAlpha(inBitmap.hasAlpha())
    }

    private fun applyMatrix(inBitmap: Bitmap, targetBitmap: Bitmap, matrix: Matrix) {
        val canvas = Canvas(targetBitmap)
        canvas.drawBitmap(inBitmap, matrix, DEFAULT_PAINT)
        clear(canvas)
    }

    private fun clear(canvas: Canvas) {
        canvas.setBitmap(null)
    }
}