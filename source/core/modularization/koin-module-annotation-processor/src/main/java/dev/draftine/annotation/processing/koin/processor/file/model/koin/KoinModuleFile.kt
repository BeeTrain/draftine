package dev.draftine.annotation.processing.koin.processor.file.model.koin

internal class KoinModuleFile(val packageName: String, val moduleName: String) {

    override fun toString(): String {
        return "$packageName $moduleName"
    }
}