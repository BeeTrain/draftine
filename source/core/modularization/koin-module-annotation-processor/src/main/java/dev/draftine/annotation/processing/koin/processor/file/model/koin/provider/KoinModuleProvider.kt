package dev.draftine.annotation.processing.koin.processor.file.model.koin.provider

import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModuleFile
import java.io.File

internal class KoinModuleProvider(
    private val path: String?,
    private val name: String,
    koinModuleFile: KoinModuleFile
) {

    private val fileBody = KoinModuleProviderBody(koinModuleFile)

    fun generate() {
        val bodyString = fileBody.toString()
        val file = File(path, name)
        file.writeText(bodyString)
    }
}