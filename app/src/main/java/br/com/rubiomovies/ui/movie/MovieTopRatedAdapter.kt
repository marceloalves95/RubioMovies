package br.com.rubiomovies.ui.movie

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiomovies.databinding.MovieAdapterBinding

/**
 * @author RubioAlves
 * Created 06/06/2021 at 17:33
 */
class MovieTopRatedAdapter<T>(private val lista: MutableList<T>) :
    RecyclerView.Adapter<MovieTopRatedAdapter<T>.MovieTopRatedAdapterViewHolder>() {

    inner class MovieTopRatedAdapterViewHolder(private val itemBinding: MovieAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: T) {


        }

    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): MovieTopRatedAdapterViewHolder {

        val itemBinding =
            MovieAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieTopRatedAdapterViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: MovieTopRatedAdapterViewHolder, position: Int) {

        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

}