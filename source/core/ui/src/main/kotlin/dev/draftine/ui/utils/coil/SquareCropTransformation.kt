package dev.draftine.ui.utils.coil

import android.graphics.Bitmap
import coil.size.Size
import coil.size.pxOrElse
import coil.transform.Transformation
import kotlin.math.max

class SquareCropTransformation : Transformation {

    override val cacheKey: String = SquareCropTransformation::class.java.name

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        val largerSize = max(
            size.width.pxOrElse { input.width },
            size.height.pxOrElse { input.height }
        )
        return CenterCropApplier.apply(input, largerSize, largerSize)
    }
}