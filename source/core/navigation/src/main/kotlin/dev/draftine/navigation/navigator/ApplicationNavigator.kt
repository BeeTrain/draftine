package dev.draftine.navigation.navigator

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.FragmentNavigator
import dev.draftine.browser.navigation.BrowserNavigator
import dev.draftine.browser.presentation.BrowserLauncher
import dev.draftine.camera.presentation.navigation.CameraNavigator
import dev.draftine.feed.presentation.navigation.FeedNavigator
import dev.draftine.feed.presentation.view.FeedFragmentDirections
import dev.draftine.imageviewer.navigation.ImageViewerNavigator
import dev.draftine.imageviewer.presentation.ImageViewer
import dev.draftine.main.presentation.navigation.MainNavigator
import dev.draftine.navigation.R
import dev.draftine.profile.presentation.navigation.ProfileNavigator
import dev.draftine.profile.presentation.view.ProfileFragmentDirections
import dev.draftine.settings.presentation.navigation.SettingsNavigator
import dev.draftine.settings.presentation.view.SettingsFragmentDirections
import dev.draftine.splash.presentation.navigation.SplashNavigator
import dev.draftine.splash.presentation.view.SplashFragmentDirections
import dev.draftine.ui.extension.openAppSettings
import dev.draftine.ui.extension.openImageFile
import dev.draftine.ui.extension.openVideoFile
import dev.draftine.ui.extension.setFadeAnim
import dev.draftine.ui.extension.setHorizontalFullInAnim
import dev.draftine.ui.extension.setModalFullAnim
import dev.draftine.ui.image.Image
import dev.draftine.utils.lifecycle.ActivityContextProvider

class ApplicationNavigator(
    private val activityContextProvider: ActivityContextProvider,
    private val browserLauncher: BrowserLauncher,
    private val imageViewer: ImageViewer
) : AppNavigator,
    SplashNavigator,
    ProfileNavigator,
    SettingsNavigator,
    BrowserNavigator,
    ImageViewerNavigator,
    MainNavigator,
    FeedNavigator,
    CameraNavigator {

    private var appNavController: NavController? = null

    override fun bindAppController(navController: NavController) {
        appNavController = navController
    }

    override fun unbindAppController() {
        appNavController = null
    }

    override fun fromSplashToMain() {
        appNavController?.navigate(
            action = SplashFragmentDirections.actionOpenMain(),
            navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.splash, true)
                .setHorizontalFullInAnim()
                .build()
        )
    }

    override fun fromProfileToSettings() {
        appNavController?.navigate(
            action = ProfileFragmentDirections.openSettings(),
            navOptions = NavOptions.Builder()
                .setModalFullAnim()
                .build()
        )
    }

    override fun closeSettings() {
        appNavController?.popBackStack()
    }

    override fun openAboutApp() {
        appNavController?.navigate(
            action = SettingsFragmentDirections.openAboutApp(),
            navOptions = NavOptions.Builder()
                .setHorizontalFullInAnim()
                .setPopUpTo(R.id.profile, false)
                .build()
        )
    }

    override fun openExchangeRateUrl(url: String) {
        openUrl(url)
    }

    override fun openTapeImageInViewer(images: List<Image>, selectedImage: Image) {
        openImageViewer(images, selectedImage)
    }

    override fun openUrl(url: String) {
        browserLauncher.launchUrl(url)
    }

    override fun openImageViewer(images: List<Image>, selectedImage: Image) {
        imageViewer.openImageViewer(images, selectedImage)
    }

    override fun openCamera() {
        appNavController?.navigate(
            action = FeedFragmentDirections.openCamera(),
            navOptions = NavOptions.Builder()
                .setHorizontalFullInAnim()
                .build()
        )
    }

    override fun openAppSettings() {
        activityContextProvider.activityContext?.openAppSettings()
    }

    override fun openImage(imageUri: Uri) {
        activityContextProvider.activityContext?.openImageFile(imageUri)
    }

    override fun openVideo(videoUri: Uri) {
        activityContextProvider.activityContext?.openVideoFile(videoUri)
    }

    private fun NavController.navigate(
        action: NavDirections,
        args: Bundle? = null,
        navExtras: FragmentNavigator.Extras? = null,
        navOptions: NavOptions? = NavOptions.Builder().setFadeAnim().build()
    ) {
        navigate(action.actionId, args, navOptions, navExtras)
    }
}