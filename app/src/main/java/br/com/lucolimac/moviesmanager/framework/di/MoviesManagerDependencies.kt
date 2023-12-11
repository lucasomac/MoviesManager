package br.com.lucolimac.moviesmanager.framework.di

import br.com.lucolimac.moviesmanager.domain.usecase.MovieUseCase
import br.com.lucolimac.moviesmanager.presentation.viewmodel.MovieViewModel
import br.com.lucolimac.moviesmanager.presentation.component.Separator
import br.com.lucolimac.moviesmanager.data.repository.MovieRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module
import br.com.lucolimac.moviesmanager.presentation.component.MovieAdapter

object MoviesManagerDependencies {
    val moviesManagerModule = module {
        viewModelOf(::MovieViewModel)
        factoryOf(::Separator)
        factoryOf(::MovieAdapter)
        factoryOf(::MovieRepositoryImpl) { bind<MovieUseCase>() }
    }
}