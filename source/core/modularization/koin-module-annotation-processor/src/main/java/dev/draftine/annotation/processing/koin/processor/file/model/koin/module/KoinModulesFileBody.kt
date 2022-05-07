package dev.draftine.annotation.processing.koin.processor.file.model.koin.module

import dev.draftine.annotation.processing.koin.processor.extension.KOIN_PROCESSOR_PACKAGE
import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModuleProvider
import dev.draftine.annotation.processing.koin.processor.file.model.koin.KoinModulesList
import dev.draftine.annotation.processing.koin.processor.file.model.koin.ListOfModule
import dev.draftine.annotation.processing.koin.processor.file.model.line.Empty
import dev.draftine.annotation.processing.koin.processor.file.model.line.NewLine
import dev.draftine.annotation.processing.koin.processor.file.model.line.Package
import dev.draftine.annotation.processing.koin.processor.file.model.line.ValueDeclaration

internal class KoinModulesFileBody(
    private val koinModuleFiles: List<KoinModuleProvider>
) {

    override fun toString(): String {
        return StringBuilder()
            .append(Package(KOIN_PROCESSOR_PACKAGE))
            .append(NewLine(Empty()))
            .append(NewLine(ValueDeclaration("koinModules")))
            .append(ListOfModule(KoinModulesList(koinModuleFiles)))
            .toString()
    }
}