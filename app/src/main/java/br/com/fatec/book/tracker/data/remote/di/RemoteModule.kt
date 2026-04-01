package br.com.fatec.book.tracker.data.remote.di

import br.com.fatec.book.tracker.data.data.data.source.JsonDataSource
import br.com.fatec.book.tracker.data.remote.api.JsonApi
import br.com.fatec.book.tracker.data.remote.data.source.JsonDataSourceRemote
import br.com.fatec.book.tracker.data.remote.service.JsonService
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteModule = module {
    single<HttpClient> {
        createHttpClient()
    }

    factoryOf(::JsonApi)
        .bind<JsonService>()

    factoryOf(::JsonDataSourceRemote)
        .bind<JsonDataSource.Remote>()
}
