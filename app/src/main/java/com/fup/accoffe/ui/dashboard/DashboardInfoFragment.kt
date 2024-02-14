package com.fup.accoffe.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentDashboardInfoBinding
import com.fup.accoffe.databinding.FragmentDryingListBinding
import com.fup.accoffe.models.EstateModel
import com.fup.accoffe.models.PlantationModel
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
        getestatebyid()
        buttonfunctions()
        backDashbordInfo()
        return root

    }

    private fun getestatebyid(){
        val estateId = arguments?.getString("estateId")
        if(estateId != null){
            db.collection("estate").document(estateId).get()
                .addOnSuccessListener { querySnapshot ->
                    val yourData = querySnapshot.toObject(EstateModel::class.java)
                    Log.d("informacion", "getestatebyid: "+ yourData)
                    binding.tvname.text=yourData?.ename.toString()
                    binding.tvyear.text=yourData?.eyear.toString()
                    binding.tvtcrop.text=yourData?.etypecrop.toString()
                    binding.tvparea.text=yourData?.eproductarea.toString()
                    binding.tvtarea.text=yourData?.etotalarea.toString()
                    binding.tvcalmendra.text=yourData?.econvertionalmendra.toString()
                    binding.tvdolar.text=yourData?.edolar.toString()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun backDashbordInfo(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.nav_home,bundle)
        }
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
        binding.btninfoGraphs.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.nav_Graphs,bundle)
        }

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DashboardInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}