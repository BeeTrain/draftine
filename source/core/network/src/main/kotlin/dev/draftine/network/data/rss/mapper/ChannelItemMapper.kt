package dev.draftine.network.data.rss.mapper

import com.prof.rssparser.Article
import dev.draftine.network.data.rss.model.ChannelItem

internal class ChannelItemMapper {

    fun map(item: Article): ChannelItem {
        return ChannelItem(
            guid = item.guid,
            title = item.title,
            author = item.author,
            link = item.link,
            pubDate = item.pubDate,
            description = item.description,
            content = item.content,
            image = item.image,
            audio = item.audio,
            video = item.video,
            sourceName = item.sourceName,
            sourceUrl = item.sourceUrl,
            categories = item.categories
        )
    }
}