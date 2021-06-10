package br.com.rubiomovies.ui.movie

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiomovies.databinding.MovieAdapterBinding

/**
 * @author RubioAlves
 * Created 06/06/2021 at 17:22
 */
class MovieLatestAdapter<T>(private val lista: MutableList<T>) : RecyclerView.Adapter<MovieLatestAdapter<T>.MovieLatestAdapterViewHolder>() {

    inner class MovieLatestAdapterViewHolder(private val itemBinding: MovieAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: T) {


        }

    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): MovieLatestAdapterViewHolder {

        val itemBinding =
            MovieAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieLatestAdapterViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: MovieLatestAdapterViewHolder, position: Int) {

        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

}