package br.com.rubiomovies.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rubiomovies.databinding.SeriesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author RubioAlves
 * Created 05/06/2021 at 15:05
 */
@AndroidEntryPoint
class SeriesFragment : Fragment() {

    private var _binding: SeriesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = SeriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


}