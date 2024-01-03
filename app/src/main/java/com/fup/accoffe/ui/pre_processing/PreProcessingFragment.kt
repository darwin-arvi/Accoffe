package com.fup.accoffe.ui.pre_processing

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
import com.fup.accoffe.adapters.PreProcessingListAdapter
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentPreProcessingBinding
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.fup.accoffe.models.EstateModel
import com.fup.accoffe.models.PlantationModel
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
 * Use the [PreProcessingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreProcessingFragment : Fragment() {

    private var _binding: FragmentPreProcessingBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance()

    private lateinit var b_year: String
    private lateinit var b_val_maq: String
    private lateinit var b_dolar: String
    private lateinit var b_val_infra: String
    private lateinit var b_promed_electrico: String
    private lateinit var b_num_costales: String
    private lateinit var b_gf_Benef: String

    val v_dolar2014 = 2000.0
    val transformidad_t1 = 26500000000000.0
    val transformidad_t2 = 22500000000000.0
    val transformidad_t5 = 23100000000.0


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
    private fun getState(){
        this.estateInfoModel.b_num_costales = estateDataModel.b_num_costales
        this.estateInfoModel.b_promed_electrico = estateDataModel.b_promed_electrico
        this.estateInfoModel.b_gf_Benef = estateDataModel.b_gf_Benef
        this.estateInfoModel.b_val_infra = estateDataModel.b_val_infra
        this.estateInfoModel.b_year = estateDataModel.b_year
        this.estateInfoModel.b_val_maq = estateDataModel.b_val_maq
        this.estateInfoModel.transformidad_t1 = transformidad_t1
        this.estateInfoModel.transformidad_t2 = transformidad_t2
        this.estateInfoModel.transformidad_t5 = transformidad_t5
        val dolar = estateDataModel.b_dolar

    }
    private fun obtainData(){

        val estateRef = db.collection("beaten").document("DlXAvMXfoMxMMUpJH9Vq")

        estateRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Document exists, you can access the data
                    val data = documentSnapshot.data

                    val info= documentSnapshot.toObject(estateDataModel::class.java)
                    if (info != null) {
                        estateDataModel=info
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
    private fun logic(){

///operaciones
        var flujo1 = ((estateDataModel.b_val_maq!! / v_dolar2014) / 30) / 118
        val t1 = (flujo1 * transformidad_t1)

        this.estateInfoModel.flujo1 = flujo1;
        this.estateInfoModel.t1 = t1;

        //const flujo2 = 14244 / 118;
        val flujo2 = estateDataModel.b_gf_Benef!! / 118;
        var t2 = (flujo2 * transformidad_t2);

        this.estateInfoModel.flujo2 = flujo2;
        this.estateInfoModel.t2 = t2;

        val flujo3 = ((estateDataModel.b_val_infra!! / v_dolar2014) / 50) / 118;
        var t3 = (flujo3 * transformidad_t1);

        this.estateInfoModel.flujo3 = flujo3;
        this.estateInfoModel.t3 = t3;

        val flujo4 = ((estateDataModel.b_promed_electrico!! * 12) / estateDataModel.b_dolar!!) / 118;
        var t4 = (flujo4 * transformidad_t1);

        this.estateInfoModel.flujo4 = flujo4;
        this.estateInfoModel.t4 = t4;

        var t5 = (estateDataModel.b_num_costales!! * transformidad_t5);
        this.estateInfoModel.t5 = t5;

        //val totales = Number.parseInt(t1 + t2 + t3 + t4 + t5); corregir
        //this.estateInfoModel.total = totales;

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPreProcessingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        editPreProcessing()
        backPreProcessing()
        return root

    }
    private fun editPreProcessing() {
        val preProcessingId = arguments?.getString("preProcessingid")

        if (preProcessingId != null) {
            binding.botonSave.text = "Update"
            db.collection("beaten").document(preProcessingId).get().addOnSuccessListener {
                // Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_SHORT).show()
                Log.d("TAG-traerdatos", "editPreProcessing: "+ it)
                binding.etBYear.setText(it.get("b_year").toString())
                binding.etBValMaq.setText(it.get("b_val_maq").toString())
                binding.etBDolar.setText(it.get("b_dolar").toString())
                binding.etBValInfra.setText(it.get("b_val_infra").toString())
                binding.etBPromedElectrico.setText(it.get("b_promed_electrico").toString())
                binding.etBNumCostales.setText(it.get("b_num_costales").toString())
                binding.etBGfBenef.setText(it.get("b_gf_Benef").toString())
            }
                .addOnFailureListener { e ->

                }
            binding.botonSave.setOnClickListener {
                initViews()
                val datosActualizados = hashMapOf(
                    "b_year" to b_year,
                    "b_val_maq" to b_val_maq,
                    "b_dolar" to b_dolar,
                    "b_val_infra" to b_val_infra,
                    "b_promed_electrico" to b_promed_electrico,
                    "b_num_costales" to b_num_costales,
                    "b_gf_Benef" to b_gf_Benef

                )
                val datosActualizadosMap: Map<String, Any> = datosActualizados
                db.collection("beaten").document(preProcessingId).update(datosActualizadosMap).addOnSuccessListener {
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
            savePreProcessing()
        }
    }
    private fun savePreProcessing(){
        binding.botonSave.setOnClickListener {
            val preProcessingCollection = db.collection("beaten")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    initViews()
                    val estateId = arguments?.getString("estateId")
                    val preProcessing = PreProcessingModel("",b_num_costales.toDouble(), b_promed_electrico.toDouble(),
                        b_val_infra.toDouble(),b_year, b_val_maq.toDouble(), b_gf_Benef.toDouble(), b_dolar.toDouble(),estateId)

                    val documentReference = preProcessingCollection.add(preProcessing).await()
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
    private fun backPreProcessing(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.botonBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.preProcessingListFragment,bundle)
        }
    }

    private fun initViews() {
        b_year= binding.etBYear.text.toString()
        b_val_maq= binding.etBValMaq.text.toString()
        b_dolar= binding.etBDolar.text.toString()
        b_val_infra= binding.etBValInfra.text.toString()
        b_promed_electrico= binding.etBPromedElectrico.text.toString()
        b_num_costales= binding.etBNumCostales.text.toString()
        b_gf_Benef = binding.etBGfBenef.text.toString()
    }

}