package dev.draftine.annotation.processing.koin.processor.file.model.line

import dev.draftine.annotation.processing.koin.processor.file.model.Line

internal class Tabulation(private val line: String) : Line {

    override fun toString(): String {
        return "\t$line"
    }
}