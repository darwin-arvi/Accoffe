package com.fup.accoffe.ui.estate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentEstateBinding
import com.fup.accoffe.models.EstateModel
import com.fup.accoffe.models.HarvestModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EstateFragment : Fragment() {

    private var _binding: FragmentEstateBinding? = null
    private val db = FirebaseFirestore.getInstance()

    private val binding get() = _binding!!

    private lateinit var ename: String
    private lateinit var eyear: String
    private lateinit var eproductarea: String
    private lateinit var etotalarea: String
    private lateinit var econvertionalmendra: String
    private lateinit var etypecrop: String
    private lateinit var edolar: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val estateViewModel =
            ViewModelProvider(this)[EstateViewModel::class.java]

        _binding = FragmentEstateBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        editEstate()
        backEstate()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
    private fun editEstate() {
        val estateId = arguments?.getString("estateid")

        if (estateId != null) {
            binding.botonSave.text = "Update"
            db.collection("estate").document(estateId).get().addOnSuccessListener {
                Log.d("TAG-traerdatos", "editEstate: "+ it)
                binding.etEname.setText(it.get("ename").toString())
                binding.etEyear.setText(it.get("eyear").toString())
                binding.etEproductarea.setText(it.get("eproductarea").toString())
                binding.etEtotalarea.setText(it.get("etotalarea").toString())
                binding.etEconvertionalmendra.setText(it.get("econvertionalmendra").toString())
                binding.etEtypecrop.setText(it.get("etypecrop").toString())
                binding.etEdolar.setText(it.get("edolar").toString())
            }
                .addOnFailureListener { e ->

                }
            binding.botonSave.setOnClickListener {
                initViews()
                val datosActualizados = hashMapOf(
                    "ename" to ename,
                    "eyear" to eyear,
                    "eproductarea" to eproductarea,
                    "etotalarea" to etotalarea,
                    "econvertionalmendra" to econvertionalmendra,
                    "etypecrop" to etypecrop,
                    "edolar" to edolar

                )
                val datosActualizadosMap: Map<String, Any> = datosActualizados
                db.collection("estate").document(estateId).update(datosActualizadosMap).addOnSuccessListener {
                    Toast.makeText(context, "Actualizado Correctamente", Toast.LENGTH_SHORT).show()
                    requireActivity().runOnUiThread {
                        Navigation.findNavController(requireView()).popBackStack()
                    }
                }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show()

                    }
            }

        }else{
            saveEstate()
        }
    }

    private fun saveEstate(){
        binding.botonSave.setOnClickListener {
            val estateCollection = db.collection("estate")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    initViews()
                    val estate = EstateModel("",econvertionalmendra.toDouble(),edolar.toDouble(),ename.toString(),
                        eproductarea.toDouble(),etotalarea.toDouble(),etypecrop.toString(),eyear.toString())

                    val documentReference = estateCollection.add(estate).await()
                    println("Document created with ID: ${documentReference.id}")
                    requireActivity().runOnUiThread {
                        Navigation.findNavController(requireView()).popBackStack()
                    }
                } catch (e: Exception) {
                    // Manejar errores si es necesario
                    e.printStackTrace()
                }
            }
        }
    }
    private fun backEstate(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.botonBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dashboardInfoFragment,bundle)
        }
    }
private fun initViews() {
    ename= binding.etEname.text.toString()
    eyear= binding.etEyear.text.toString()
    eproductarea= binding.etEproductarea.text.toString()
    etotalarea= binding.etEtotalarea.text.toString()
    econvertionalmendra= binding.etEconvertionalmendra.text.toString()
    etypecrop= binding.etEtypecrop.text.toString()
    edolar= binding.etEdolar.text.toString()
}
}