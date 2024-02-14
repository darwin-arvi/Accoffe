package com.fup.accoffe.ui.pre_processing

import android.app.AlertDialog
import android.content.DialogInterface
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
import com.fup.accoffe.adapters.HarvestListAdapter
import com.fup.accoffe.adapters.PlantationListAdapter
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
        backPreProcessinglist()
        return root
    }
    private fun backPreProcessinglist(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dashboardInfoFragment,bundle)
        }
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
                val estateId = arguments?.getString("estateId")
                Log.d("DashboardInfoFragment", "Received estateId: $estateId")
                val collection = db.collection(collectionName).whereEqualTo("estateId", estateId).get().await()
                val dataList = mutableListOf<PreProcessingModel>()

                for (document in collection.documents) {
                    val yourData = document.toObject(PreProcessingModel::class.java)
                    if (yourData != null) {
                        val id = document.id
                        val modelo = yourData.copy(id = id)
                        Log.d("llega", "Received estateId: $modelo")
                        dataList.add(modelo)
                    }
                }

                // Now you can use dataList, which contains all documents in the collection
                activity?.runOnUiThread {
                    val adapter = PreProcessingListAdapter(dataList,

                        onClickEdit = {
                        val bundle = Bundle()
                        bundle.putString("preProcessingid", it)
                        Navigation.findNavController(requireView()).navigate(R.id.nav_pre_processing,bundle)

                    },onClickInfo = {
                            val bundle = Bundle()
                            bundle.putString("preProcessingid", it)
                            Navigation.findNavController(requireView()).navigate(R.id.navPreProcessingInfoFragment,bundle)
                        },
                        onClickDelete = {

                            val builder = AlertDialog.Builder(requireContext())

                            builder.setTitle("Confirmación")
                            builder.setMessage("¿Estás seguro de que deseas borrar?")

                            builder.setPositiveButton("Sí") { dialogInterface: DialogInterface, i: Int ->

                        db.collection(collectionName).document(it).delete()
                            .addOnSuccessListener {
                                Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_SHORT).show()
                                fetchAllDataFromFirestore(collectionName)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error al Eliminar", Toast.LENGTH_SHORT).show()

                            }
                        }

                                builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

                        }

                        val dialog = builder.create()
                    dialog.show()


                })
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