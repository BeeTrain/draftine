package dev.draftine.browser.presentation

import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import dev.draftine.browser.R
import dev.draftine.utils.lifecycle.ActivityContextProvider
import dev.draftine.utils.resources.ResourcesProvider

class BrowserLauncher(
    private val activityContextProvider: ActivityContextProvider,
    private val resourcesProvider: ResourcesProvider
) {

    fun launchUrl(url: String) {
        val context = requireNotNull(activityContextProvider.activityContext)

        CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(prepareColorScheme())
            .setStartAnimations(context, R.anim.slide_in_bottom, R.anim.fade_out)
            .setExitAnimations(context, R.anim.fade_in, R.anim.slide_out_bottom)
            .build()
            .launchUrl(context, Uri.parse(url))
    }

    private fun prepareColorScheme(): CustomTabColorSchemeParams {
        val surfaceColor = resourcesProvider.getAttrColor(R.attr.colorSurface)
        return CustomTabColorSchemeParams.Builder()
            .setToolbarColor(surfaceColor)
            .setNavigationBarColor(surfaceColor)
            .build()
    }
}