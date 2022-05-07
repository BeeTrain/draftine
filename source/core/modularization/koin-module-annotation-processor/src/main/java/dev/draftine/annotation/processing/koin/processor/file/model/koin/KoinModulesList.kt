package dev.draftine.annotation.processing.koin.processor.file.model.koin

internal class KoinModulesList(private val koinModules: List<KoinModuleProvider>) {

    override fun toString(): String {
        return StringBuilder()
            .appendKoinModulesList()
            .toString()
    }

    private fun StringBuilder.appendKoinModulesList(): StringBuilder {
        koinModules
            .map { KoinModuleLine(it.name) }
            .forEach { append(it) }
        return this
    }
}