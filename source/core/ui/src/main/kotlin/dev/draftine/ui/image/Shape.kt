package dev.draftine.ui.image

import androidx.annotation.Px

sealed interface Shape {

    object Circle : Shape

    data class Rounded(
        @Px val topLeft: Float = 0F,
        @Px val topRight: Float = 0F,
        @Px val bottomLeft: Float = 0F,
        @Px val bottomRight: Float = 0F
    ) : Shape {

        constructor(@Px cornerRadius: Float = 0F) : this(cornerRadius, cornerRadius, cornerRadius, cornerRadius)
    }
}