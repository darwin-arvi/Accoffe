package com.fup.accoffe.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.fup.accoffe.R
import com.fup.accoffe.adapters.DryingListAdapter
import com.fup.accoffe.adapters.EstateListAdapter
import com.fup.accoffe.databinding.FragmentDashboardBinding
import com.fup.accoffe.models.DryingModel
import com.fup.accoffe.models.EstateModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        fetchAllDataFromFirestore("estate")
        addNewEstate()
        return root
    }

    private fun addNewEstate(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnAddEstate.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.nav_estate,bundle)
        }
    }
    private fun fetchAllDataFromFirestore(collectionName: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val collection = db.collection(collectionName).get().await()


                val dataList = mutableListOf<EstateModel>()

                for (document in collection.documents) {
                    val yourData = document.toObject(EstateModel::class.java)
                    if (yourData != null) {
                        val id = document.id
                        val modelo = yourData.copy(id = id)
                        dataList.add(modelo)
                    }
                }

                // Now you can use dataList, which contains all documents in the collection
                activity?.runOnUiThread {
                    val adapter = EstateListAdapter(dataList, onClickDelete = {

                        db.collection(collectionName).document(it).delete()
                            .addOnSuccessListener {
                                Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_SHORT).show()
                                fetchAllDataFromFirestore(collectionName)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error al Eliminar", Toast.LENGTH_SHORT).show()
                            } },
                        onClickInfo = {
                        Log.d("clickonnfoid", "este es el id de x: $it")
                        val bundle = Bundle()
                        bundle.putString("estateId", it)
                        Navigation.findNavController(requireView()).navigate(R.id.dashboardInfoFragment,bundle)

                    },
                        onClickEdit = {
                            val bundle = Bundle()
                            bundle.putString("estateid", it)
                            Navigation.findNavController(requireView()).navigate(R.id.nav_estate,bundle)
                        })

                    binding.rvEstates.layoutManager = LinearLayoutManager(context)
                    binding.rvEstates.adapter = adapter
                }
                Log.d("getting data", "fetchAllDataFromFirestore: $dataList")
            } catch (e: Exception) {
                println("Error fetching data: $e")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}