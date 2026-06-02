package br.com.fatec.book.tracker.data.data.di

import br.com.fatec.book.tracker.data.data.repository.AuthRepositoryImpl
import br.com.fatec.book.tracker.data.data.repository.JsonRepositoryImpl
import br.com.fatec.book.tracker.data.data.repository.TokenRepositoryImpl
import br.com.fatec.book.tracker.domain.repository.AuthRepository
import br.com.fatec.book.tracker.domain.repository.JsonRepository
import br.com.fatec.book.tracker.domain.repository.TokenRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::JsonRepositoryImpl)
        .bind<JsonRepository>()
    singleOf(::AuthRepositoryImpl)
        .bind<AuthRepository>()
    singleOf(::TokenRepositoryImpl)
        .bind<TokenRepository>()
}
