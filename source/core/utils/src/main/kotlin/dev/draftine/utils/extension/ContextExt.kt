package dev.draftine.utils.extension

import android.content.Context
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

fun Context.getMainExecutorExt(): Executor {
    return ContextCompat.getMainExecutor(this)
}