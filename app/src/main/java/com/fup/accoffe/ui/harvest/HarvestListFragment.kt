package com.fup.accoffe.ui.harvest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fup.accoffe.R
import com.fup.accoffe.adapters.HarvestListAdapter
import com.fup.accoffe.databinding.FragmentDashboardBinding
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentHarvestListBinding
import com.fup.accoffe.models.HarvestModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HarvestListFragment : Fragment() {

    private var _binding: FragmentHarvestListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    companion object {
        fun newInstance() = HarvestListFragment()
    }

    private lateinit var viewModel: HarvestListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHarvestListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fetchAllDataFromFirestore("harvest")
        addNewHarvest()
        backHarvest()
        return root
    }
    private fun backHarvest() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dashboardInfoFragment, bundle)
        }
    }
    private fun addNewHarvest(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnAddHarvest.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.nav_harvest,bundle)
        }
    }

    private fun fetchAllDataFromFirestore(collectionName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val estateId = arguments?.getString("estateId")
                Log.d("DashboardInfoFragment", "Received estateId: $estateId")
                val collection = db.collection(collectionName).whereEqualTo("estateId", estateId)
                    .get()
                    .await()

                val dataList = mutableListOf<HarvestModel>()

                for (document in collection.documents) {
                    val yourData = document.toObject(HarvestModel::class.java)
                    if (yourData != null) {
                        val id = document.id
                        val modelo = yourData.copy(id = id)
                        dataList.add(modelo)
                    }
                }

                // Now you can use dataList, which contains all documents in the collection
                activity?.runOnUiThread {
                    val adapter = HarvestListAdapter(dataList)
                    binding.rvHarvest.layoutManager = LinearLayoutManager(context)
                    binding.rvHarvest.adapter = adapter
                }
                Log.d("getting data", "fetchAllDataFromFirestore: $dataList")
            } catch (e: Exception) {
                println("Error fetching data: $e")
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HarvestListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}