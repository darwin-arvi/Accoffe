package com.fup.accoffe.ui.pre_processing

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
import com.fup.accoffe.adapters.PreProcessingListAdapter
import com.fup.accoffe.databinding.FragmentDashboardBinding
import com.fup.accoffe.databinding.FragmentPreProcessingBinding
import com.fup.accoffe.databinding.FragmentPreProcessingListBinding
import com.fup.accoffe.models.PreProcessingModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class PreProcessingListFragment : Fragment() {

    private var _binding: FragmentPreProcessingListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    companion object {
        fun newInstance() = PreProcessingListFragment()
    }

    private lateinit var viewModel: PreProcessingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreProcessingListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fetchAllDataFromFirestore("beaten")
        addNewPre_processing()
        return root
    }

    private fun addNewPre_processing(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnAddPreProcessing.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.nav_pre_processing,bundle)
        }
    }
    private fun fetchAllDataFromFirestore(collectionName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val collection = db.collection(collectionName).get().await()
                val dataList = mutableListOf<PreProcessingModel>()

                for (document in collection.documents) {
                    val yourData = document.toObject(PreProcessingModel::class.java)
                    if (yourData != null) {
                        val id = document.id
                        val modelo = yourData.copy(id = id)
                        dataList.add(modelo)
                    }
                }

                // Now you can use dataList, which contains all documents in the collection
                activity?.runOnUiThread {
                    val adapter = PreProcessingListAdapter(dataList)
                    binding.rvPreProcessing.layoutManager = LinearLayoutManager(context)
                    binding.rvPreProcessing.adapter = adapter

                }
                Log.d("getting data", "fetchAllDataFromFirestore: $dataList")
            } catch (e: Exception) {
                println("Error fetching data: $e")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PreProcessingListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}