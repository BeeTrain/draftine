package dev.draftine.annotation.processing.koin.processor.extension

import dev.draftine.annotation.processing.koin.processor.model.koin.provider.KoinModuleProvider
import java.util.Locale
import javax.lang.model.element.Element

internal fun Element.formatName(): String {
    return simpleName
        .toString()
        .removePrefix("get")
        .removeSuffix("\$annotations")
        .replaceFirstChar { it.lowercase(Locale.getDefault()) }
}

internal fun Element.toKoinModuleProvider(): KoinModuleProvider {
    val name = simpleName.toString()
        .removeSuffix("Kt")
        .replaceFirstChar { it.lowercase(Locale.getDefault()) }

    return KoinModuleProvider(name)
}