package br.com.lucolimac.moviesmanager.framework.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.lucolimac.moviesmanager.data.datasource.MovieDao
import br.com.lucolimac.moviesmanager.data.model.MovieModel

@Database(entities = [MovieModel::class], version = 5)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteLinkDAO(): MovieDao

    companion object {
        const val DATABASE_NAME = "movie_manager"
    }
}