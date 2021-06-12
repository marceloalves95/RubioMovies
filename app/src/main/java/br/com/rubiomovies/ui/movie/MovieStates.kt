package br.com.rubiomovies.ui.movie

import br.com.rubiomovies.data.remote.model.Pages

/**
 * @author RubioAlves
 * Created 06/06/2021 at 18:45
 */
sealed class MovieStates {
    class Sucess(val response: Pages?) : MovieStates()
    class Loading(val isLoading: Boolean) : MovieStates()
    class Error(val message: String) : MovieStates()
    object Empty : MovieStates()
}