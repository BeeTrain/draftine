package dev.draftine.annotation.processing.koin.processor.file.model.line

import dev.draftine.annotation.processing.koin.processor.file.model.Line

internal class Package(private val packageName: String) : Line {

    override fun toString() = "package $packageName"
}