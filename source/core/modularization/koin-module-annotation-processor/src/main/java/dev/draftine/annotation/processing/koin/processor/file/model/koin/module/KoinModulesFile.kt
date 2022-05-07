package dev.draftine.annotation.processing.koin.processor.file.model.koin.module

import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModuleProvider
import java.io.File

internal class KoinModulesFile(
    private val path: String?,
    private val name: String,
    koinModuleProviders: List<KoinModuleProvider>
) {

    private val fileBody = KoinModulesFileBody(koinModuleProviders)

    fun generate() {
        val bodyString = fileBody.toString()
        val file = File(path, name)
        file.writeText(bodyString)
    }
}