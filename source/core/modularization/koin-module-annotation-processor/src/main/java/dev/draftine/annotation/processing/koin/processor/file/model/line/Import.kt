package dev.draftine.annotation.processing.koin.processor.file.model.line

import dev.draftine.annotation.processing.koin.processor.file.model.Line

internal class Import(private val importName: String) : Line {

    override fun toString() = "import $importName"
}