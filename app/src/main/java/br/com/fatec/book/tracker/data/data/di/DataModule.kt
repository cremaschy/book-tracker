package br.com.fatec.book.tracker.data.data.di

import br.com.fatec.book.tracker.data.data.repository.JsonRepositoryImpl
import br.com.fatec.book.tracker.domain.repository.JsonRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::JsonRepositoryImpl)
        .bind<JsonRepository>()
}
