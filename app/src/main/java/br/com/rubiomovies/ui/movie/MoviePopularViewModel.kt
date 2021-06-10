package br.com.rubiomovies.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rubiomovies.api.MovieApi
import br.com.rubiomovies.ui.movie.MovieStates.Empty
import br.com.rubiomovies.ui.movie.MovieStates.Error
import br.com.rubiomovies.ui.movie.MovieStates.Sucess
import br.com.rubiomovies.ui.movie.MovieStates.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * @author RubioAlves
 * Created 06/06/2021 at 18:44
 */
@HiltViewModel
class MoviePopularViewModel @Inject constructor(private val movie: MovieApi) : ViewModel() {

    private val _state = MutableStateFlow<MovieStates>(Empty)
    val state: StateFlow<MovieStates> = _state

    fun emit(value: MovieStates) {
        _state.value = value
    }

    fun getMoviePopular() {

        viewModelScope.launch {

            try {
                emit(Loading(true))
                delay(1000)
                val response = movie.getMoviePopular()
                with(response) {
                    if (isSuccessful) {
                        emit(Sucess(body()))
                    } else {
                        emit(Error("Erro: ${code()}"))
                    }
                }
            } catch (exception: Exception) {
                emit(Error("Sem Internet"))
            }
            emit(Loading(true))
        }

    }

}