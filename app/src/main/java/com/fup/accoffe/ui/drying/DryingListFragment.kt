package com.fup.accoffe.ui.drying

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fup.accoffe.R
import com.fup.accoffe.adapters.DryingListAdapter
import com.fup.accoffe.adapters.HarvestListAdapter
import com.fup.accoffe.adapters.PlantationListAdapter
import com.fup.accoffe.databinding.FragmentDashboardBinding
import com.fup.accoffe.databinding.FragmentDryingBinding
import com.fup.accoffe.databinding.FragmentDryingListBinding
import com.fup.accoffe.models.DryingModel
import com.fup.accoffe.models.HarvestModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DryingListFragment : Fragment() {
    private var _binding: FragmentDryingListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    companion object {
        fun newInstance() = DryingListFragment()
    }

    private lateinit var viewModel: DryingListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDryingListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fetchAllDataFromFirestore("drying")
        addNewDrying()
        backDrying()
        return root

    }


    private fun backDrying(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dashboardInfoFragment, bundle)
        }
    }
    private fun addNewDrying(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnAddDrying.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.nav_drying,bundle)

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


                val dataList = mutableListOf<DryingModel>()

                for (document in collection.documents) {
                    val yourData = document.toObject(DryingModel::class.java)
                    if (yourData != null) {
                        val id = document.id
                        val modelo = yourData.copy(id = id)
                        dataList.add(modelo)
                    }
                }

                // Now you can use dataList, which contains all documents in the collection
                activity?.runOnUiThread {
                    val adapter = DryingListAdapter(dataList, onClickDelete = {

                        db.collection(collectionName).document(it).delete()
                            .addOnSuccessListener {
                                Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_SHORT).show()
                                fetchAllDataFromFirestore(collectionName)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error al Eliminar", Toast.LENGTH_SHORT).show()

                            }

                    },onClickInfo = {
                        val bundle = Bundle()
                        bundle.putString("dryingid", it)
                        Navigation.findNavController(requireView()).navigate(R.id.navDryingInfoFragment,bundle)
                    },
                        onClickEdit = {
                        val bundle = Bundle()
                        bundle.putString("dryingid", it)
                        Navigation.findNavController(requireView()).navigate(R.id.nav_drying,bundle)

                    })
                    binding.rvDrying.layoutManager = LinearLayoutManager(context)
                    binding.rvDrying.adapter = adapter
                }
                Log.d("getting data", "fetchAllDataFromFirestore: $dataList")
            } catch (e: Exception) {
                println("Error fetching data: $e")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DryingListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}