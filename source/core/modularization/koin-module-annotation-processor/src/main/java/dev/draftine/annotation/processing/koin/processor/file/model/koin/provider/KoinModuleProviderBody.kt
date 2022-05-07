package dev.draftine.annotation.processing.koin.processor.file.model.koin.provider

import dev.draftine.annotation.processing.koin.processor.extension.KOIN_PROCESSOR_PACKAGE
import dev.draftine.annotation.processing.koin.processor.extension.KOIN_PROVIDER_SUFFIX
import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModuleFile
import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModuleImport
import dev.draftine.annotation.processing.koin.processor.file.model.line.Empty
import dev.draftine.annotation.processing.koin.processor.file.model.line.NewLine
import dev.draftine.annotation.processing.koin.processor.file.model.line.Package
import dev.draftine.annotation.processing.koin.processor.file.model.line.ValueDeclaration

internal class KoinModuleProviderBody(private val koinModuleFile: KoinModuleFile) {

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