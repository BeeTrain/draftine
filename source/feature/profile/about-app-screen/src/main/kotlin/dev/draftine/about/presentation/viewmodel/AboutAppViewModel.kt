package dev.draftine.about.presentation.viewmodel

import dev.draftine.about.domain.interactor.AboutAppInteractor
import dev.draftine.about.presentation.model.AppInfoModel
import dev.draftine.about.presentation.viewmodel.mapper.AppInfoMapper
import dev.draftine.arch.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AboutAppViewModel(
    private val aboutAppInteractor: AboutAppInteractor,
    private val appInfoMapper: AppInfoMapper
) : BaseViewModel() {

    val appInfoData = MutableStateFlow(loadAppInfo())

    private fun loadAppInfo(): List<AppInfoModel> {
        return aboutAppInteractor.loadAppInfo()
            .let { appInfoMapper.map(it) }
    }
}