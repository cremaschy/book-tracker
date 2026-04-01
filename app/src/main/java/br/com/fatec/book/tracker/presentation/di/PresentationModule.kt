package br.com.fatec.book.tracker.presentation.di

import br.com.fatec.book.tracker.presentation.feature.placeholder.list.PlaceholderListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::PlaceholderListViewModel)
}
