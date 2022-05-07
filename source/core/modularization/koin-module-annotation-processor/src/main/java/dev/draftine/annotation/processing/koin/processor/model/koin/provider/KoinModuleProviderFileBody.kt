package dev.draftine.annotation.processing.koin.processor.model.koin.provider

import dev.draftine.annotation.processing.koin.processor.extension.KOIN_PROCESSOR_PACKAGE
import dev.draftine.annotation.processing.koin.processor.extension.KOIN_PROVIDER_SUFFIX
import dev.draftine.annotation.processing.koin.processor.model.koin.file.KoinModuleFile
import dev.draftine.annotation.processing.koin.processor.model.koin.file.KoinModuleImport
import dev.draftine.annotation.processing.koin.processor.model.line.Empty
import dev.draftine.annotation.processing.koin.processor.model.line.NewLine
import dev.draftine.annotation.processing.koin.processor.model.line.Package
import dev.draftine.annotation.processing.koin.processor.model.line.ValueDeclaration

internal class KoinModuleProviderFileBody(private val koinModuleFile: KoinModuleFile) {

    override fun toString(): String {
        return StringBuilder()
            .append(Package(KOIN_PROCESSOR_PACKAGE))
            .append(NewLine(Empty()))
            .append(KoinModuleImport(koinModuleFile))
            .append(NewLine(Empty()))
            .append(NewLine(ValueDeclaration("${koinModuleFile.moduleName}$KOIN_PROVIDER_SUFFIX")))
            .append(koinModuleFile.moduleName)
            .toString()
    }
}