package dev.draftine.imageviewer.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.imageviewer.presentation.ImageViewer
import org.koin.dsl.module

@KoinModule
val imageViewerModule = module {

    factory { ImageViewer(get()) }
}