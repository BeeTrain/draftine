package dev.draftine.annotation.processing.koin.processor.model.line

import dev.draftine.annotation.processing.koin.processor.model.Line

internal class ValueDeclaration(private val name: String) : Line {

    override fun toString() = "val $name = "
}