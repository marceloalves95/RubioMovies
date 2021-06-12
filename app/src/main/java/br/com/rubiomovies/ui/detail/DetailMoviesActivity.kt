package br.com.rubiomovies.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.rubiomovies.R
import br.com.rubiomovies.databinding.ActivityMoviesDetailBinding
import br.com.rubiomovies.data.remote.model.MovieDados
import br.com.rubiomovies.utilities.Constants
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        init()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home){
            //Toast.makeText(this@DetailMoviesActivity, "Teste", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun init(){


        val movie: MovieDados? = intent.getParcelableExtra("dados")
        val url = "${Constants.BASE_URI_MOVIE_IMAGES}${movie?.poster}"
        with(binding){
        Glide.with(this@DetailMoviesActivity).load(url).into(posterImage)
            movieDescription.text = movie?.detalhes
            supportActionBar?.setTitle(movie?.titulo)
        }

    }



}
