import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author RubioAlves
 * Created 05/06/2021 at 13:24
 */
class MainAdapter(private val lista: MutableList<Model>) :
    RecyclerView.Adapter<MainAdapter.MainAdapterViewHolder>() {

    inner class MainAdapterViewHolder(private val itemBinding: MainAdapterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(model: Model) {


        }

    }

    override fun onCreateViewHolder(
        parent: android.view.ViewGroup,
        viewType: Int
    ): MainAdapterViewHolder {

        val itemBinding =
            MainAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainAdapterViewHolder(itemBinding)

    }

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {

        holder.bind(lista[position])
    }

    override fun getItemCount(): Int = lista.size

}