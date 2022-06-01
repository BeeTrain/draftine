package dev.draftine.network.data.rss

import android.content.Context
import com.prof.rssparser.Parser
import java.util.concurrent.TimeUnit

class ParserProvider(
    private val context: Context
) {

    fun provide(): Parser {
        return Parser.Builder()
            .context(context)
            .charset(Charsets.UTF_8)
            .cacheExpirationMillis(TimeUnit.HOURS.toMillis(1))
            .build()
    }
}