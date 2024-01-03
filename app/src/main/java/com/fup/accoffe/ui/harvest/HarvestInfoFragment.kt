package com.fup.accoffe.ui.harvest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentHarvestInfoBinding

class HarvestInfoFragment : Fragment() {



    private lateinit var viewModel: HarvestInfoViewModel
    private var _binding: FragmentHarvestInfoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHarvestInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.tvflujoanual.text="2"


        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HarvestInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}