package dev.draftine.network.data.rss.mapper

import dev.draftine.network.data.rss.model.Channel
import com.prof.rssparser.Channel as LibraryChannel

internal class ChannelMapper(
    private val itemMapper: ChannelItemMapper
) {

    fun map(channel: LibraryChannel): Channel {
        return Channel(
            title = channel.title,
            link = channel.link,
            description = channel.description,
            lastBuildDate = channel.lastBuildDate,
            updatePeriod = channel.updatePeriod,
            items = channel.articles.map { itemMapper.map(it) }
        )
    }
}