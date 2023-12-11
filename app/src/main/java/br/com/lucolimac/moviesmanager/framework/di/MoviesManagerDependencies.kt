package br.com.lucolimac.moviesmanager.framework.di

import br.com.lucolimac.moviesmanager.presentation.viewmodel.MovieViewModel
import br.com.lucolimac.moviesmanager.presentation.component.Separator
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import br.com.lucolimac.moviesmanager.presentation.component.MovieAdapter

object MoviesManagerDependencies {
    val moviesManagerModule = module {
        viewModelOf(::MovieViewModel)
        factoryOf(::Separator)
        factoryOf(::MovieAdapter)
    }
}