package br.com.lucolimac.moviesmanager.framework.di

import androidx.room.Room
import br.com.lucolimac.moviesmanager.data.datasource.MovieDao
import br.com.lucolimac.moviesmanager.data.repository.MovieRepositoryImpl
import br.com.lucolimac.moviesmanager.domain.usecase.MovieUseCase
import br.com.lucolimac.moviesmanager.framework.datasource.MovieDatabase
import br.com.lucolimac.moviesmanager.presentation.component.MovieAdapter
import br.com.lucolimac.moviesmanager.presentation.component.GenderAdapter
import br.com.lucolimac.moviesmanager.presentation.component.Separator
import br.com.lucolimac.moviesmanager.presentation.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object MoviesManagerDependencies {
    val moviesManagerModule = module {
        single {
            Room.databaseBuilder(
                androidContext(), MovieDatabase::class.java, MovieDatabase.DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
        }
        single<MovieDao> {
            get<MovieDatabase>().favoriteLinkDAO()
        }
        viewModelOf(::MovieViewModel)
        factoryOf(::Separator)
        singleOf(::MovieAdapter)
        factoryOf(::GenderAdapter)
        factoryOf(::MovieRepositoryImpl) { bind<MovieUseCase>() }
    }
}