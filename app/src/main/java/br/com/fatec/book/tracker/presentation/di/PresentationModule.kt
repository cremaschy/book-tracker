package br.com.fatec.book.tracker.presentation.di

import br.com.fatec.book.tracker.presentation.MainViewModel
import br.com.fatec.book.tracker.presentation.feature.adicionar.AdicionarLivroViewModel
import br.com.fatec.book.tracker.presentation.feature.biblioteca.BibliotecaViewModel
import br.com.fatec.book.tracker.presentation.feature.detalhe.DetalheViewModel
import br.com.fatec.book.tracker.presentation.feature.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::AdicionarLivroViewModel)
    viewModelOf(::BibliotecaViewModel)
    viewModelOf(::DetalheViewModel)
}
