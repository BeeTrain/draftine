package dev.draftine.about.domain.repository

import dev.draftine.about.domain.model.AppInfo

interface AboutAppInfoRepository {

    fun getAppInfo(): List<AppInfo>
}