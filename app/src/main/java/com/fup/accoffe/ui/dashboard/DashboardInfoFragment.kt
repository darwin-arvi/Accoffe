package com.fup.accoffe.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentDashboardInfoBinding
import com.fup.accoffe.databinding.FragmentDryingListBinding
import com.google.firebase.firestore.FirebaseFirestore

class DashboardInfoFragment : Fragment() {


    private lateinit var viewModel: DashboardInfoViewModel
    private var _binding: FragmentDashboardInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        buttonfunctions()


        return root

    }
    private fun buttonfunctions(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.plantationManagement.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.plantationListFragment,bundle)
        }

        binding.btndrayingmanagement.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dryingListFragment,bundle)
        }
        binding.btnharvestmanagement.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.harvestListFragment,bundle)
        }
        binding.btnpreprocessingmanagement.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.preProcessingListFragment,bundle)
        }

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}