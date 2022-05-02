package dev.draftine.ui.extension

import androidx.navigation.NavOptions
import dev.draftine.ui.R

fun NavOptions.Builder.setHorizontalFullInAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.slide_in_right)
    setExitAnim(R.anim.slide_out_left)
    setPopEnterAnim(R.anim.slide_in_left)
    setPopExitAnim(R.anim.slide_out_right)

    return this
}

fun NavOptions.Builder.setHorizontalOnlyInAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.slide_in_right)
    setExitAnim(R.anim.partial_slide_out_left)
    setPopEnterAnim(R.anim.partial_slide_in_left)

    return this
}

fun NavOptions.Builder.setModalInAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.slide_in_bottom)

    return this
}

fun NavOptions.Builder.setModalOutAnim(): NavOptions.Builder {
    setPopExitAnim(R.anim.slide_out_bottom)

    return this
}

fun NavOptions.Builder.setModalFullAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.slide_in_bottom)
    setExitAnim(R.anim.fade_out)
    setPopEnterAnim(R.anim.fade_in)
    setPopExitAnim(R.anim.slide_out_bottom)

    return this
}

fun NavOptions.Builder.setFadeAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.fade_in)
    setPopExitAnim(R.anim.fade_out)

    return this
}

fun NavOptions.Builder.setFadeInHorizontalOutAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.fade_in)
    setPopEnterAnim(R.anim.slide_in_left)
    setPopExitAnim(R.anim.slide_out_right)

    return this
}

fun NavOptions.Builder.setFadeInModalOutAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.card_fade_in)
    setPopExitAnim(R.anim.card_slide_out_bottom)

    return this
}

fun NavOptions.Builder.setSlideInFromBottomFullAnim(): NavOptions.Builder {
    setEnterAnim(R.anim.slide_up)
    setExitAnim(R.anim.fade_out)
    setPopEnterAnim(R.anim.fade_in)
    setPopExitAnim(R.anim.slide_down)

    return this
}