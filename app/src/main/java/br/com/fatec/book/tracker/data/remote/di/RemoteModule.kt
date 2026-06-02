package br.com.fatec.book.tracker.data.remote.di

import br.com.fatec.book.tracker.data.data.data.source.JsonDataSource
import br.com.fatec.book.tracker.data.data.data.source.TokenDataSource
import br.com.fatec.book.tracker.data.remote.api.JsonApi
import br.com.fatec.book.tracker.data.remote.api.TokenApi
import br.com.fatec.book.tracker.data.remote.data.source.JsonDataSourceRemote
import br.com.fatec.book.tracker.data.remote.data.source.TokenDataSourceRemote
import br.com.fatec.book.tracker.data.remote.service.JsonService
import br.com.fatec.book.tracker.data.remote.service.TokenService
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteModule = module {
    single<HttpClientEngine> {
        OkHttp.create()
    }
    factory<HttpClient> {
        createHttpClient(get(), get())
    }

    factoryOf(::JsonApi)
        .bind<JsonService>()

    factoryOf(::JsonDataSourceRemote)
        .bind<JsonDataSource.Remote>()

    factoryOf(::TokenApi)
        .bind<TokenService>()

    factoryOf(::TokenDataSourceRemote)
        .bind<TokenDataSource.Remote>()
}
