package br.com.rubiomovies.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rubiomovies.data.remote.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 * @author RubioAlves
 * Created 09/06/2021 at 12:34
 */
@HiltViewModel
class MovieNowPlayingViewModel@Inject constructor(private val repository: MovieRepository) : ViewModel(){

    private val _state = MutableStateFlow<MovieStates>(MovieStates.Empty)
    val state: StateFlow<MovieStates> = _state

    fun emit(value: MovieStates) {
        _state.value = value
    }

    fun getMovieNowPlaying() {

        viewModelScope.launch {

            try {
                emit(MovieStates.Loading(true))
                delay(1000)
                val response = repository.getNowPlaying()
                with(response) {
                    if (isSuccessful) {
                        emit(MovieStates.Sucess(body()))
                    } else {
                        emit(MovieStates.Error("Erro: ${code()}"))
                    }
                }
            } catch (exception: Exception) {
                emit(MovieStates.Error("Sem Internet"))
            }
            emit(MovieStates.Loading(true))
        }

    }
}