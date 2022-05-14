package dev.draftine.utils.lifecycle

import android.app.Activity
import android.app.Application

class ActivityLifecycler(application: Application) {

    private val lifecycleCallback by lazy(LazyThreadSafetyMode.NONE) { createLifecycleCallback() }

    private var started: Int = 0

    private var stopped: Int = 0

    val isApplicationVisible: Boolean
        get() = started > stopped

    init {
        application.registerActivityLifecycleCallbacks(lifecycleCallback)
    }

    private fun createLifecycleCallback(): LifecycleCallback {
        return object : LifecycleCallback() {

            override fun onActivityStarted(activity: Activity) {
                started++
            }

            override fun onActivityStopped(activity: Activity) {
                stopped++
            }
        }
    }
}