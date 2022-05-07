package dev.draftine.annotation.processing.koin.processor.file.model.koin

import dev.draftine.annotation.processing.koin.processor.file.model.line.Import
import dev.draftine.annotation.processing.koin.processor.file.model.line.NewLine

internal class KoinModuleImport(
    private val koinModuleFile: KoinModuleFile
) {

    override fun toString(): String {
        return NewLine(
            Import("${koinModuleFile.packageName}.${koinModuleFile.moduleName}")
        ).toString()
    }
}