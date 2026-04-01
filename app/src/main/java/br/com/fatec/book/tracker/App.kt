package br.com.fatec.book.tracker

import android.app.Application
import br.com.fatec.book.tracker.data.data.di.dataModule
import br.com.fatec.book.tracker.data.local.di.localModule
import br.com.fatec.book.tracker.data.remote.di.remoteModule
import br.com.fatec.book.tracker.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(remoteModule + localModule + dataModule + presentationModule)
        }
    }
}