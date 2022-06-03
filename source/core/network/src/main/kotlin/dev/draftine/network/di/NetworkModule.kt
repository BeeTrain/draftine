package dev.draftine.network.di

import dev.draftine.annotation.processing.koin.annotation.KoinModule
import dev.draftine.network.data.moshi.MoshiProvider
import dev.draftine.network.data.okhttp.OkHttpProvider
import dev.draftine.network.data.rss.ParserProvider
import dev.draftine.network.data.rss.RssLoader
import dev.draftine.network.data.rss.RssLoaderImpl
import dev.draftine.network.data.rss.mapper.ChannelItemMapper
import dev.draftine.network.data.rss.mapper.ChannelMapper
import org.koin.dsl.module

@KoinModule
val networkModule = module {

    single { OkHttpProvider().provide() }
    single { MoshiProvider().provide() }
    single<RssLoader> {
        RssLoaderImpl(
            parser = get(),
            channelMapper = get()
        )
    }
    single { ParserProvider(context = get()).provide() }
    single { ChannelMapper(get()) }
    single { ChannelItemMapper() }
}