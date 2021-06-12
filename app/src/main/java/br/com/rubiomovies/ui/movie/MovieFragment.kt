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
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiomovies.R
import br.com.rubiomovies.databinding.MovieFragmentBinding
import br.com.rubiomovies.data.remote.model.MovieDados
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

    private fun initMovies(recyclerView: RecyclerView, adapter: MovieAdapter){

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        adapter.onItemClick = { position->
            val titulo = adapter.lista[position].title
            val poster = adapter.lista[position].backdrop_path
            val detalhes = adapter.lista[position].overview
            passarDados(poster, titulo, detalhes)
        }

    }

    private fun init(){

        popularViewModel.getMoviePopular()
        lifecycleScope.launchWhenStarted {

          popularViewModel.state.collect { states->

              with(states){
                  when(this){
                      is Error -> {
                          mensagemAviso(message)
                      }
                      is Sucess -> {
                          response?.let {pages->
                              val moviePopularAdapter = MovieAdapter(pages.results)
                              initMovies(binding.filmesPopulares, moviePopularAdapter)
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
                      is Error -> {
                          mensagemAviso(message)
                      }
                      is Sucess -> {
                          response?.let {pages->
                             val movieNow = MovieAdapter(pages.results)
                              initMovies(binding.filmesCartaz, movieNow)
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
                      is Error -> {
                          mensagemAviso(message)
                      }
                      is Sucess -> {
                          response?.let {pages->
                              val movieUpComing = MovieAdapter(pages.results)
                              initMovies(binding.filmesEstreias, movieUpComing)
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
                      is Error -> {
                          mensagemAviso(message)
                      }
                      is Sucess -> {
                          response?.let {pages->
                              val movieTopRated = MovieAdapter(pages.results)
                              initMovies(binding.filmesAvaliacoes, movieTopRated)
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