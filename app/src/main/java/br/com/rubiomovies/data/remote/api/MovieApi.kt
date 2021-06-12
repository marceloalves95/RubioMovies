package br.com.rubiomovies.data.remote.api

import br.com.rubiomovies.BuildConfig.MOVIE_API_KEY
import br.com.rubiomovies.data.remote.model.Pages
import br.com.rubiomovies.utilities.Constants.PORTUGUESE_BRAZIL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author RubioAlves
 * Created 06/06/2021 at 17:52
 */
interface MovieApi {

    @GET("/3/movie/popular")
    suspend fun getMoviePopular(@Query("language") language:String = PORTUGUESE_BRAZIL, @Query("api_key") apiKey: String = MOVIE_API_KEY, @Query("page") page:Int = 1):Response<Pages>
    @GET("/3/movie/now_playing")
    suspend fun getMovieNowPlaying(@Query("language") language:String = PORTUGUESE_BRAZIL, @Query("api_key") apiKey: String = MOVIE_API_KEY):Response<Pages>
    @GET("/3/movie/upcoming")
    suspend fun getMovieUpComing(@Query("language") language:String = PORTUGUESE_BRAZIL, @Query("api_key") apiKey: String = MOVIE_API_KEY):Response<Pages>
    @GET("/3/movie/top_rated")
    suspend fun getMovieTopRated(@Query("language") language:String = PORTUGUESE_BRAZIL, @Query("api_key") apiKey: String = MOVIE_API_KEY):Response<Pages>



}