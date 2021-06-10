package br.com.rubiomovies.ui.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiomovies.databinding.MovieAdapterBinding
import br.com.rubiomovies.domain.Movie
import br.com.rubiomovies.utilities.Constants.BASE_URI_MOVIE_IMAGES
import com.bumptech.glide.Glide

/**
 * @author RubioAlves
 * Created 06/06/2021 at 17:26
 */
class MovieAdapter(val lista: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>() {

    inner class MovieAdapterViewHolder(private val itemBinding: MovieAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(movie: Movie) {

            val url = "$BASE_URI_MOVIE_IMAGES${movie.poster_path}"
            with(itemBinding){
                Glide.with(itemView.context).load(url).into(imagemCartaz)
                cardView.setOnClickListener {

                    onItemClick?.invoke(bindingAdapterPosition)


                }



            }



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {

        val itemBinding = MovieAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieAdapterViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {

        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

    var onItemClick: ((Int) -> Unit)? = null

}