package br.com.fatec.book.tracker.data.remote.di

import br.com.fatec.book.tracker.data.data.data.source.BibliotecaDataSource
import br.com.fatec.book.tracker.data.data.data.source.TokenDataSource
import br.com.fatec.book.tracker.data.remote.api.BibliotecaApi
import br.com.fatec.book.tracker.data.remote.api.TokenApi
import br.com.fatec.book.tracker.data.remote.data.source.BibliotecaDataSourceRemote
import br.com.fatec.book.tracker.data.remote.data.source.TokenDataSourceRemote
import br.com.fatec.book.tracker.data.remote.service.BibliotecaService
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
    factoryOf(::TokenApi)
        .bind<TokenService>()

    factoryOf(::TokenDataSourceRemote)
        .bind<TokenDataSource.Remote>()

    factoryOf(::BibliotecaApi)
        .bind<BibliotecaService>()
    factoryOf(::BibliotecaDataSourceRemote)
        .bind<BibliotecaDataSource.Remote>()
}
