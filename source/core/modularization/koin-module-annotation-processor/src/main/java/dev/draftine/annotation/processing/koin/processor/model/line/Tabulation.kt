package dev.draftine.annotation.processing.koin.processor.model.line

import dev.draftine.annotation.processing.koin.processor.model.Line

internal class Tabulation(private val line: String) : Line {

    override fun toString() = "\t$line"
}