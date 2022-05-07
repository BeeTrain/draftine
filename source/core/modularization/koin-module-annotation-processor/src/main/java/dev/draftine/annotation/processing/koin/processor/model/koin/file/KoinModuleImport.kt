package dev.draftine.annotation.processing.koin.processor.model.koin.file

import dev.draftine.annotation.processing.koin.processor.model.line.Import
import dev.draftine.annotation.processing.koin.processor.model.line.NewLine

internal class KoinModuleImport(private val koinModuleFile: KoinModuleFile) {

    override fun toString(): String {
        return NewLine(
            Import(koinModuleFile.toString())
        ).toString()
    }
}