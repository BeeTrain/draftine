package dev.draftine.annotation.processing.koin.processor.file.model.koin

import dev.draftine.annotation.processing.koin.processor.file.model.line.NewLine
import dev.draftine.annotation.processing.koin.processor.file.model.line.Tabulation

internal class KoinModuleLine(private val koinModule: String) {

    override fun toString(): String {
        return NewLine(
            Tabulation("${koinModule},")
        ).toString()
    }
}