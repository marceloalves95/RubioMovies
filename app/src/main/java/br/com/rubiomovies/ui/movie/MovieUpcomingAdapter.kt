package br.com.rubiomovies.ui.movie

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import br.com.rubiomovies.databinding.MovieAdapterBinding

/**
 * @author RubioAlves
 * Created 06/06/2021 at 17:34
 */
class MovieUpcomingAdapter<T>(private val lista: MutableList<T>) :
    RecyclerView.Adapter<MovieUpcomingAdapter<T>.MovieUpcomingAdapterViewHolder>() {

    inner class MovieUpcomingAdapterViewHolder(private val itemBinding: MovieAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: T) {


        }

    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): MovieUpcomingAdapterViewHolder {

        val itemBinding =
            MovieAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieUpcomingAdapterViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: MovieUpcomingAdapterViewHolder, position: Int) {

        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

}