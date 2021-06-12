package br.com.rubiomovies.data.remote.repository

import br.com.rubiomovies.data.remote.api.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author RubioAlves
 * Created 10/06/2021 at 10:57
 */
@Singleton
class MovieRepository @Inject constructor(private val movie: MovieApi)  {

    suspend fun getMoviePopular() = movie.getMoviePopular()
    suspend fun getNowPlaying() = movie.getMovieNowPlaying()
    suspend fun getUpComing() = movie.getMovieUpComing()
    suspend fun getTopRated() = movie.getMovieTopRated()

}