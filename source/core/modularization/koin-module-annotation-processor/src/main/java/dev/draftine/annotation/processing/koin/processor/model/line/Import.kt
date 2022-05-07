package dev.draftine.annotation.processing.koin.processor.model.line

import dev.draftine.annotation.processing.koin.processor.model.Line

internal class Import(private val importName: String) : Line {

    override fun toString() = "import $importName"
}