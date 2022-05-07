package dev.draftine.annotation.processing.koin.processor.generator

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.annotation.processing.koin.processor.extension.KAPT_OPTION
import dev.draftine.annotation.processing.koin.processor.extension.formatName
import dev.draftine.annotation.processing.koin.processor.extension.toProviderName
import dev.draftine.annotation.processing.koin.processor.model.koin.file.KoinModuleFile
import dev.draftine.annotation.processing.koin.processor.model.koin.provider.KoinModuleProviderFIle
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element

internal class KoinModuleProviderGenerator(
    private val roundEnvironment: RoundEnvironment,
    private val processingEnvironment: ProcessingEnvironment
) {

    fun generate() {
        val elements = roundEnvironment.getElementsAnnotatedWith(KoinModule::class.java)
        if (elements.isNotEmpty()) {
            elements.forEach { it.toKoinModuleFile().generateKoinModuleProvider() }
        }
    }

    private fun Element.toKoinModuleFile(): KoinModuleFile {
        val moduleName = formatName()
        val packageName = processingEnvironment.elementUtils.getPackageOf(this).toString()

        return KoinModuleFile(packageName, moduleName)
    }

    private fun KoinModuleFile.generateKoinModuleProvider() {
        val koinModuleProvider = KoinModuleProviderFIle(
            processingEnvironment.options[KAPT_OPTION],
            moduleName.toProviderName(),
            this
        )
        koinModuleProvider.generate()
    }
}