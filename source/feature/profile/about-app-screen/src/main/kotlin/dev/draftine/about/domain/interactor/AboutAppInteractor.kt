package dev.draftine.about.domain.interactor

import dev.draftine.about.domain.model.AppInfo
import dev.draftine.about.domain.repository.AboutAppInfoRepository

class AboutAppInteractor(private val aboutAppInfoRepository: AboutAppInfoRepository) {

    fun loadAppInfo(): List<AppInfo> {
        return aboutAppInfoRepository.getAppInfo()
    }
}