package dev.draftine.annotation.processing.koin.processor.model.line

import dev.draftine.annotation.processing.koin.processor.model.Line

internal class Package(private val packageName: String) : Line {

    override fun toString() = "package $packageName"
}