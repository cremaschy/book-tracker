package br.com.fatec.book.tracker.data.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import br.com.fatec.book.tracker.data.data.data.source.AuthDataSource
import br.com.fatec.book.tracker.data.data.data.source.JsonDataSource
import br.com.fatec.book.tracker.data.local.data.source.AuthDataSourceLocal
import br.com.fatec.book.tracker.data.local.data.source.JsonDataSourceLocal
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val PREFERENCES_NAME = "book_tracker_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME,
)

val localModule = module {
    single<DataStore<Preferences>> {
        androidContext().dataStore
    }
    singleOf(::JsonDataSourceLocal)
        .bind<JsonDataSource.Local>()

    singleOf(::AuthDataSourceLocal)
        .bind<AuthDataSource.Local>()
}
