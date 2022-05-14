package dev.draftine.utils.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class ActivityContextProvider(application: Application) {

    private val lifecycleCallback by lazy(LazyThreadSafetyMode.NONE) { createLifecycleCallback() }

    var activityContext: Context? = null

    init {
        application.registerActivityLifecycleCallbacks(lifecycleCallback)
    }

    private fun createLifecycleCallback(): LifecycleCallback {
        return object : LifecycleCallback() {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                activityContext = activity
            }

            override fun onActivityDestroyed(activity: Activity) {
                activityContext = null
            }
        }
    }
}