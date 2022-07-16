package dev.draftine.utils.camera

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraHost: LifecycleOwner {

    val previewView: PreviewView
}