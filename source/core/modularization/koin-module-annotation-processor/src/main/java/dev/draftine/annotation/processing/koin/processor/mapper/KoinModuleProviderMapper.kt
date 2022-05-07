package dev.draftine.annotation.processing.koin.processor.mapper

import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModuleProvider
import javax.lang.model.element.Element

internal class KoinModuleProviderMapper {

    fun map(element: Element): KoinModuleProvider {
        val name = element.simpleName
            .toString()
            .removeSuffix("Kt")
            .decapitalize()

        return KoinModuleProvider(name)
    }
}