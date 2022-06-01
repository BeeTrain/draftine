package dev.draftine.network.data.rss

import com.prof.rssparser.Parser
import dev.draftine.network.data.rss.mapper.ChannelMapper
import dev.draftine.network.data.rss.model.Channel

internal class RssLoaderImpl(
    private val parser: Parser,
    private val channelMapper: ChannelMapper
) : RssLoader {

    override suspend fun loadRss(url: String): Channel {
        return parser.getChannel(url)
            .run { channelMapper.map(this) }
    }
}