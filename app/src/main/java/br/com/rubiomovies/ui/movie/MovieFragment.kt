package br.com.rubiomovies.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rubiomovies.R
import br.com.rubiomovies.databinding.MovieFragmentBinding
import br.com.rubiomovies.domain.MovieDados
import br.com.rubiomovies.ui.detail.DetailMoviesActivity
import br.com.rubiomovies.ui.main.MainActivity
import br.com.rubiomovies.ui.movie.MovieStates.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val popularViewModel:MoviePopularViewModel by viewModels()
    private val nowPlayingViewModel:MovieNowPlayingViewModel by viewModels()
    private val upComingViewModel:MovieUpComingViewModel by viewModels()
    private val topRatedViewModel:MovieTopRatedViewModel by viewModels()
    private lateinit var moviePopularAdapter:MovieAdapter
    private lateinit var movieNow:MovieAdapter
    private lateinit var movieUpComing:MovieAdapter
    private lateinit var movieTopRated:MovieAdapter
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = MovieFragmentBinding.inflate(inflater, container, false)

       init()

        return binding.root


    }

    private fun initMoviePopulares(){

        binding.filmesPopulares.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = moviePopularAdapter
        }

        moviePopularAdapter.onItemClick = { position->

            val titulo = moviePopularAdapter.lista[position].title
            val poster = moviePopularAdapter.lista[position].backdrop_path
            val detalhes = moviePopularAdapter.lista[position].overview
            passarDados(poster, titulo, detalhes)

        }

    }
    private fun initMovieNow(){

        binding.filmesCartaz.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieNow
        }

        movieNow.onItemClick = { position->

            val titulo = movieNow.lista[position].title
            val poster = movieNow.lista[position].backdrop_path
            val detalhes = movieNow.lista[position].overview
            passarDados(poster, titulo, detalhes)

        }

    }
    private fun initMovieUpComing(){

        binding.filmesEstreias.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieUpComing
        }

        movieUpComing.onItemClick = { position->

            val titulo = movieUpComing.lista[position].title
            val poster = movieUpComing.lista[position].backdrop_path
            val detalhes = movieUpComing.lista[position].overview
            passarDados(poster, titulo, detalhes)

        }

    }
    private fun initMovieTopRated(){

        binding.filmesAvaliacoes.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = movieTopRated
        }

        movieTopRated.onItemClick = { position->

            val titulo = movieTopRated.lista[position].title
            val poster = movieTopRated.lista[position].backdrop_path
            val detalhes = movieTopRated.lista[position].overview
            passarDados(poster, titulo, detalhes)

        }

    }

    private fun init(){

        popularViewModel.getMoviePopular()
        lifecycleScope.launchWhenStarted {
            
            popularViewModel.state.collect { states->

                with(states){
                    when(this){
                        Empty -> {

                        }
                        is Error -> {
                            mensagemAviso(message)
                        }
                        is Loading -> {
                        }
                        is Sucess -> {
                            response?.let {pages->
                                moviePopularAdapter = MovieAdapter(pages.results)
                                initMoviePopulares()
                            }
                        }
                    }
                }

            }


        }

        nowPlayingViewModel.getMovieNowPlaying()
        lifecycleScope.launchWhenStarted {

            nowPlayingViewModel.state.collect { states->

                with(states){
                    when(this){
                        Empty -> {

                        }
                        is Error -> {
                            mensagemAviso(message)
                        }
                        is Loading -> {

                        }
                        is Sucess -> {
                            response?.let {pages->
                                movieNow = MovieAdapter(pages.results)
                                initMovieNow()
                            }
                        }
                    }
                }

            }
        }
        upComingViewModel.getMovieUpComing()
        lifecycleScope.launchWhenStarted {

            upComingViewModel.state.collect { states->

                with(states){
                    when(this){
                        Empty -> {

                        }
                        is Error -> {
                            mensagemAviso(message)
                        }
                        is Loading -> {

                        }
                        is Sucess -> {
                            response?.let {pages->
                                movieUpComing = MovieAdapter(pages.results)
                                initMovieUpComing()
                            }
                        }
                    }
                }

            }
        }
        topRatedViewModel.getMovieTopRated()
        lifecycleScope.launchWhenStarted {

            topRatedViewModel.state.collect { states->

                with(states){
                    when(this){
                        Empty -> {

                        }
                        is Error -> {
                            mensagemAviso(message)
                        }
                        is Loading -> {

                        }
                        is Sucess -> {
                            response?.let {pages->
                                movieTopRated = MovieAdapter(pages.results)
                                initMovieTopRated()
                            }
                        }
                    }
                }

            }
        }


    }
    private fun mensagemAviso(mensagem: String) {
        Snackbar.make(requireView(), mensagem, Snackbar.LENGTH_SHORT).show()
    }

    private fun passarDados(poster:String, titulo:String, detalhes:String){

        val dados = MovieDados(poster, titulo, detalhes)
        val intent = Intent(activity, DetailMoviesActivity::class.java)
        intent.putExtra("dados",dados)
        startActivity(intent)
        (activity as MainActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    }



}