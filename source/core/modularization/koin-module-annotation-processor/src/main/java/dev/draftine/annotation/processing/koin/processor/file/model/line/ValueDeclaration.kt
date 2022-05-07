package dev.draftine.annotation.processing.koin.processor.file.model.line

import dev.draftine.annotation.processing.koin.processor.file.model.Line

internal class ValueDeclaration(private val name: String) : Line {

    override fun toString(): String {
        return "val $name = "
    }
}