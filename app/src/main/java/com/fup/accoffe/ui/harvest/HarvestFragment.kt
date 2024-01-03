package com.fup.accoffe.ui.harvest

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
import com.fup.accoffe.adapters.EstateListAdapter
import com.fup.accoffe.adapters.HarvestListAdapter
import com.fup.accoffe.databinding.FragmentDryingBinding
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.fup.accoffe.models.EstateModel
import com.fup.accoffe.models.HarvestModel
import com.fup.accoffe.models.PreProcessingModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HarvestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HarvestFragment : Fragment() {

    private var _binding: FragmentHarvestBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance()

    private lateinit var h_year: String
    private lateinit var h_maq_man: String
    private lateinit var h_combustible: String
    private lateinit var h_transport: String
    private lateinit var l_Pjornal_recole: String

    private var transformidad_f17 = 6700000000.0
    private var transformidad_f18 = 111111.0
    private var transformidad_f19 = 22500000000000.0
    private var transformidad_c20 = 22500000000000.0
    private var anio_analizado_c1 = 1.0 // le asigne un valor para que no genere error
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        energyModel = EnergyModel()
        getState()
        obtainData()

        arguments?.let {

        }
    }

    private fun getState() {
        this.estateInfoModel.transformidad_f17 = transformidad_f17
        this.estateInfoModel.transformidad_f18 = transformidad_f18
        this.estateInfoModel.transformidad_f19 = transformidad_f19
        this.estateInfoModel.transformidad_c20 = transformidad_c20

        this.estateInfoModel.anio_analizado_c1 = anio_analizado_c1
        Log.d("harvestResults", "getState: esta funcionado claro que si!!" + estateInfoModel.anio_analizado_c1)
    }

    private fun obtainData() {
        val estateRef = db.collection("harvest").document("wnt3M5dQdDsit2ctRM1f")

        estateRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Document exists, you can access the data
                    val data = documentSnapshot.data

                    val info = documentSnapshot.toObject(estateDataModel::class.java)
                    if (info != null) {
                        estateDataModel = info
                    }
                    logic()

                    Log.d("TAG", "Document data: ${data}")
                } else {
                    Log.d("TAG", "Document does not exist")
                }
            }
            .addOnFailureListener { e ->
                Log.e("TAG", "Error getting document", e)
            }
    }

    private fun logic() {

        ///operaciones
        //COSECHA F17
        val emergia_p_f17 = estateDataModel.h_maq_man!! * transformidad_f17
        //COSECHA F18
        var anio_analizado_c1 = estateDataModel.h_combustible!! * 39500000
        val emergia_p_f18 = anio_analizado_c1 * transformidad_f18
        //COSECHA F19
        val emergia_p_f19 = estateDataModel.h_transport!! * transformidad_f19
        //COSECHA F20
        val emergia_p_f20 = estateDataModel.l_Pjornal_recole!! * transformidad_c20
        //TOTAL COSECHA
        var total_c = emergia_p_f17 + emergia_p_f18 + emergia_p_f19 + emergia_p_f20

        this.estateInfoModel.total_c = total_c

        this.energyModel.emergia_p_f17 = emergia_p_f17
        this.energyModel.emergia_p_f18 = emergia_p_f18
        this.energyModel.emergia_p_f19 = emergia_p_f19
        this.energyModel.emergia_p_f20 = emergia_p_f20

        this.estateInfoModel.transformidad_f17 = transformidad_f17
        this.estateInfoModel.transformidad_f18 = transformidad_f18
        this.estateInfoModel.transformidad_f19 = transformidad_f19
        this.estateInfoModel.transformidad_c20 = transformidad_c20

        this.estateInfoModel.anio_analizado_c1 = anio_analizado_c1
        Log.d("harvestable", "datos solicitados: ${estateInfoModel.transformidad_f17} ${estateDataModel.h_maq_man} ${energyModel.emergia_p_f17}")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHarvestBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        editHarvest()
        backHarvest()
        return root
    }

    private fun editHarvest() {
        val harvestId = arguments?.getString("harvestid")

        if (harvestId != null) {
            binding.botonSave.text = "Update"
            db.collection("harvest").document(harvestId).get().addOnSuccessListener {
               // Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_SHORT).show()
                Log.d("TAG-traerdatos", "editHarvest: "+ it)
                binding.etHYear.setText(it.get("h_year").toString())
                binding.etHMaqMan.setText(it.get("h_maq_man").toString())
                binding.etHCombustible.setText(it.get("h_combustible").toString())
                binding.etHTransport.setText(it.get("h_transport").toString())
                binding.etLPjornalRecole.setText(it.get("l_Pjornal_recole").toString())
            }
                .addOnFailureListener { e ->

                }
            binding.botonSave.setOnClickListener {
                initViews()
                val datosActualizados = hashMapOf(
                    "h_year" to h_year,
                    "h_maq_man" to h_maq_man.toDouble(),
                    "h_combustible" to h_combustible.toDouble(),
                    "h_transport" to h_transport.toDouble(),
                    "l_Pjornal_recole" to l_Pjornal_recole.toDouble()

                )
                val datosActualizadosMap: Map<String, Any> = datosActualizados
                db.collection("harvest").document(harvestId).update(datosActualizadosMap).addOnSuccessListener {
                    Toast.makeText(context, "Actualizado Correctamente", Toast.LENGTH_SHORT).show()
                    requireActivity().runOnUiThread {
                        Navigation.findNavController(requireView()).popBackStack()
                        //val bundle = Bundle()
                        //bundle.putString("estateId", arguments?.getString("estateId"))
                        //Navigation.findNavController(requireView()).navigate(R.id.harvestListFragment,bundle)
                    }
                }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show()

                    }
            }

        }else{
            saveHarvest()
        }
    }

    private fun saveHarvest() {
        binding.botonSave.setOnClickListener {
            val harvestCollection = db.collection("harvest")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    initViews()
                    val estateId = arguments?.getString("estateId")
                    val harvest = HarvestModel(
                        "",
                        h_year,
                        h_maq_man.toDouble(),
                        h_combustible.toDouble(),
                        h_transport.toDouble(),
                        l_Pjornal_recole.toDouble(),
                        estateId
                    )

                    val documentReference = harvestCollection.add(harvest).await()
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

    private fun backHarvest() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.botonBack.setOnClickListener {
            //val bundle = Bundle()
            //bundle.putString("estateId", estateId)
            //Navigation.findNavController(requireView()).navigate(R.id.harvestListFragment, bundle)
            Navigation.findNavController(requireView()).popBackStack()

        }
    }

    private fun initViews() {
        h_year = binding.etHYear.text.toString()
        h_maq_man = binding.etHMaqMan.text.toString()
        h_combustible = binding.etHCombustible.text.toString()
        h_transport = binding.etHTransport.text.toString()
        l_Pjornal_recole = binding.etLPjornalRecole.text.toString()
    }

}