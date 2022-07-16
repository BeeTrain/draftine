package dev.draftine.utils.camera

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.PendingRecording
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import dev.draftine.utils.extension.getMainExecutorExt
import dev.draftine.utils.extension.ignoreError
import dev.draftine.utils.permissions.PermissionsManager
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor

private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
private const val IMAGE_MIME_TYPE = "image/jpeg"
private const val VIDEO_MIME_TYPE = "video/mp4"
private const val IMAGE_RELATIVE_PATH = "Pictures/Draftine-Image"
private const val VIDEO_RELATIVE_PATH = "Movies/Draftine-Video"

class CameraController(
    private val context: Context,
    private val permissionsManager: PermissionsManager
) {

    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null

    private val cameraExecutor: Executor
        get() = context.getMainExecutorExt()

    fun startCamera(cameraHost: CameraHost) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener(
            createCameraListener(cameraProviderFuture, cameraHost.previewView, cameraHost),
            cameraExecutor
        )
    }

    fun takePhoto(imageCallback: ImageCallback) {
        val imageCapture = imageCapture ?: return

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, prepareFileName())
            put(MediaStore.MediaColumns.MIME_TYPE, IMAGE_MIME_TYPE)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, IMAGE_RELATIVE_PATH)
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        imageCapture.takePicture(
            outputOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    imageCallback.onSaved(output)
                }

                override fun onError(exception: ImageCaptureException) {
                    imageCallback.onError(exception)
                }
            }
        )
    }

    fun captureVideo(videoCallback: VideoCallback) {
        val videoCapture = this.videoCapture ?: return

        videoCallback.beforeStarted()

        val currentRecording = recording
        if (currentRecording != null) {
            currentRecording.stop()
            recording = null
            return
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, prepareFileName())
            put(MediaStore.MediaColumns.MIME_TYPE, VIDEO_MIME_TYPE)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Video.Media.RELATIVE_PATH, VIDEO_RELATIVE_PATH)
            }
        }

        val mediaStoreOutputOptions = MediaStoreOutputOptions
            .Builder(context.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues)
            .build()

        recording = videoCapture.output
            .prepareRecording(context, mediaStoreOutputOptions)
            .withAudioIfAllowed()
            .start(cameraExecutor) { recordEvent ->
                when (recordEvent) {
                    is VideoRecordEvent.Start -> videoCallback.onStarted(recordEvent)
                    is VideoRecordEvent.Finalize -> {
                        if (recordEvent.hasError()) {
                            recording?.close()
                            recording = null
                        }

                        videoCallback.onFinalized(recordEvent)
                    }
                }
            }
    }

    private fun createCameraListener(
        cameraProviderFuture: ListenableFuture<ProcessCameraProvider>,
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ): Runnable {
        return Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also { it.setSurfaceProvider(previewView.surfaceProvider) }

            imageCapture = ImageCapture.Builder().build()

            val qualitySelector = QualitySelector.from(
                Quality.HIGHEST,
                FallbackStrategy.higherQualityOrLowerThan(Quality.SD)
            )
            val recorder = Recorder.Builder()
                .setQualitySelector(qualitySelector)
                .build()
            videoCapture = VideoCapture.withOutput(recorder)

            ignoreError {
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture,
                    videoCapture
                )
            }
        }
    }

    private fun prepareFileName(): String {
        return SimpleDateFormat(FILENAME_FORMAT, Locale.getDefault())
            .format(System.currentTimeMillis())
    }

    @Suppress("MissingPermission")
    private fun PendingRecording.withAudioIfAllowed(): PendingRecording {
        return apply {
            if (permissionsManager.isGranted(Manifest.permission.RECORD_AUDIO)) {
                withAudioEnabled()
            }
        }
    }

    interface ImageCallback {

        fun onSaved(output: ImageCapture.OutputFileResults)

        fun onError(exception: ImageCaptureException)
    }

    interface VideoCallback {

        fun beforeStarted()

        fun onStarted(recordEvent: VideoRecordEvent.Start)

        fun onFinalized(recordEvent: VideoRecordEvent.Finalize)
    }
}