package dev.draftine.network.data.rss

import dev.draftine.network.data.rss.model.Channel

interface RssLoader {

    suspend fun loadRss(url: String): Channel
}