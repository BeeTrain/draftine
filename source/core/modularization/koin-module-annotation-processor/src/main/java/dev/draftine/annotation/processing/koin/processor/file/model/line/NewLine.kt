package dev.draftine.annotation.processing.koin.processor.file.model.line

import dev.draftine.annotation.processing.koin.processor.file.model.Line

internal class NewLine(private val line: Line) : Line {

    override fun toString() = "\n$line"
}