package br.com.lucolimac.moviesmanager.data.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.lucolimac.moviesmanager.data.model.MovieModel

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createMovie(movieModel: MovieModel)

    @Query("SELECT * FROM moviemodel ORDER BY name")
    suspend fun getAllMovies(): List<MovieModel>

    @Update
    suspend fun updateMovie(movieModel: MovieModel)

    @Delete
    suspend fun deleteMovie(movieModel: MovieModel)
}