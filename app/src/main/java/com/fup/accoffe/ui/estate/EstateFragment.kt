package com.fup.accoffe.ui.estate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fup.accoffe.databinding.FragmentEstateBinding

class EstateFragment : Fragment() {

    private var _binding: FragmentEstateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val estateViewModel =
            ViewModelProvider(this)[EstateViewModel::class.java]

        _binding = FragmentEstateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        estateViewModel.text.observe(viewLifecycleOwner) {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}