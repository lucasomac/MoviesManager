package br.com.lucolimac.moviesmanager

import android.app.Application
import br.com.lucolimac.moviesmanager.framework.di.MoviesManagerDependencies.moviesManagerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class MoviesManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            allowOverride(true)
            androidLogger()
            // Reference Android context
            androidContext(this@MoviesManagerApplication)
            // Load modules
            modules(listOf(moviesManagerModule))
        }
    }
}