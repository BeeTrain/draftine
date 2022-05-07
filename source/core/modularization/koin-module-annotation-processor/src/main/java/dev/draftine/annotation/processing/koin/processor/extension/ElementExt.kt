package dev.draftine.annotation.processing.koin.processor.extension

import java.util.Locale
import javax.lang.model.element.Element

internal fun Element.formatName(): String {
    return simpleName
        .toString()
        .removePrefix("get")
        .removeSuffix("\$annotations")
        .replaceFirstChar { it.lowercase(Locale.getDefault()) }
}