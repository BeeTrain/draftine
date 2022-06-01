package dev.draftine.network.data.rss.model

data class Channel(
    val title: String?,
    val link: String?,
    val description: String?,
    val lastBuildDate: String?,
    val updatePeriod: String?,
    val items: List<ChannelItem>
)
