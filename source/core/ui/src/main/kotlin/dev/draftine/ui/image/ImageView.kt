package dev.draftine.ui.image

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation

class ImageView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    fun loadImage(image: Image?) {
        when (image) {
            is DrawableImage -> loadDrawableImage(image)
            is UrlImage -> loadUrlImage(image)
            null -> setImageDrawable(null)
        }
    }

    private fun loadDrawableImage(image: DrawableImage) {
        if (image.drawable == null) {
            setImageDrawable(null)
        } else {
            load(image.drawable) {
                applyShape(image.shape)
            }
        }
    }

    private fun loadUrlImage(image: UrlImage) {
        if (image.url.isEmpty()) {
            setImageDrawable(null)
        } else {
            load(image.url) {
                applyShape(image.shape)
            }
        }
    }

    private fun ImageRequest.Builder.applyShape(shape: Shape?) {
        when (shape) {
            is Shape.Circle -> transformations(CircleCropTransformation())
            is Shape.Rounded -> {
                transformations(
                    RoundedCornersTransformation(
                        shape.topLeft,
                        shape.topRight,
                        shape.bottomLeft,
                        shape.bottomRight
                    )
                )
            }
            else -> Unit
        }
    }
}