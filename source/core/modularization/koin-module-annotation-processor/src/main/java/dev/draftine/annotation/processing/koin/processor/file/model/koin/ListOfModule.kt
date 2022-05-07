package dev.draftine.annotation.processing.koin.processor.file.model.koin

internal class ListOfModule(private val listItems: String) {

    constructor(koinModulesList: KoinModulesList) : this(koinModulesList.toString())

    override fun toString() = "listOf<org.koin.core.module.Module>( $listItems \n)"
}