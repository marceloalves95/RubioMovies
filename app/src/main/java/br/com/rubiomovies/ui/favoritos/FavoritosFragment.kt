package br.com.rubiomovies.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rubiomovies.databinding.FavoritosFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author RubioAlves
 * Created 05/06/2021 at 15:06
 */
@AndroidEntryPoint
class FavoritosFragment : Fragment() {

    private var _binding: FavoritosFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}