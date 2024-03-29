package com.fup.accoffe.ui.drying

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
import com.fup.accoffe.adapters.EstateListAdapter
import com.fup.accoffe.databinding.FragmentDryingBinding
import com.fup.accoffe.models.DryingModel
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.fup.accoffe.models.EstateModel
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
 * Use the [DryingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DryingFragment : Fragment() {

    private var _binding: FragmentDryingBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance()

    private lateinit var d_year: String
    private lateinit var d_val_maq: String
    private lateinit var p_transpiracion: String
    private lateinit var d_gf_Benef: String
    private lateinit var d_produccion: String
    private lateinit var d_combustible: String
    private lateinit var p_densidad2: String
    private lateinit var p_insolation: String
    private lateinit var d_dias_secado: String
    private lateinit var d_val_infra: String
    private lateinit var p_enegia_lib: String
    private lateinit var p_admosfra: String
    private lateinit var d_k: String
    private lateinit var d_promedio_electrico: String
    private lateinit var l_Pdolar: String
    private lateinit var p_densidadA: String
    private lateinit var d_patio_secado: String
    private lateinit var area_finca: String
    private lateinit var p_albedo: String
    private lateinit var p_viento: String


    private var transformidad = 1.0
    private var transformidad_f2 = 2520.0
    private var transformidad_s3 = 145000.0
    private var transformidad_s4 = 86000.0
    private var transformidad_s5 = 2420000000.0
    private var transformidad_s6 = 26500000000000.0
    private var transformidad_s7 = 22500000000000.0
    private var transformidad_s8 = 111111.0
    private var transformidad_s9 = 26500000000000.0
    private var transformidad_s30 = 26500000000000.0
    private var v_dolar2014 = 2000.0


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

        this.estateInfoModel.d_year = estateDataModel.d_year;
        this.estateInfoModel.d_produccion = estateDataModel.d_produccion;
        this.estateInfoModel.d_dias_secado = estateDataModel.d_dias_secado;
        this.estateInfoModel.d_k = estateDataModel.d_k;
        this.estateInfoModel.d_patio_secado = estateDataModel.d_patio_secado;
        this.estateInfoModel.d_val_maq = estateDataModel.d_val_maq;
        this.estateInfoModel.d_gf_Benef = estateDataModel.d_gf_Benef;
        this.estateInfoModel.d_combustible = estateDataModel.d_combustible;
        this.estateInfoModel.d_val_infra = estateDataModel.d_val_infra;
        this.estateInfoModel.d_promedio_electrico = estateDataModel.d_promedio_electrico;
    }

    private fun obtainData() {
        val estateRef = db.collection("drying").document("HvKwMbQCauJrwf0kp3h4")

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

        //FILA21
        val insolacion_s = estateDataModel.p_insolation!! * 3600000
        val insolacion_diez = insolacion_s * 10
        val flujo_anual_s1 = insolacion_diez * (1 - estateDataModel.p_albedo!!) * estateDataModel.area_finca!!
        var emergia_s1 = flujo_anual_s1 * transformidad

        //FILA22
        val flujo_anual_s2 = estateDataModel.area_finca!! * estateDataModel.p_admosfra!! * estateDataModel.p_densidadA!! *
        estateDataModel.p_viento!!
        var emergia_s2 = flujo_anual_s2 * transformidad_f2

        //FILA23
        val area_finca_1 = 10//////estateData.area_finca
        val evapo_energ = area_finca_1 * estateDataModel.p_transpiracion!! * estateDataModel.p_densidad2!! *
        estateDataModel.p_enegia_lib!!
        val flujo_anual_s3 = (evapo_energ / 365) * 10
        var emergia_s3 = flujo_anual_s3 * transformidad_s3

        //FILA24
        val prod_perg = estateDataModel.d_produccion!! / 5
        val prod_anio = (prod_perg * 2) / 1000
        //const water_cons = prod_anio / 1000
        val flujo_anual_s4 = prod_anio * estateDataModel.d_k!! * estateDataModel.p_enegia_lib!!
        var emergia_s4 = flujo_anual_s4 * transformidad_s4

        //FILA25
        val flujo_anual_s5 = estateDataModel.d_patio_secado!! * 1000
        var emergia_s5 = flujo_anual_s5 * transformidad_s5

        //FILA26
        val flujo_anual_s6 = ((estateDataModel.d_val_maq!! / v_dolar2014) / 30) / 118
        var emergia_s6 = flujo_anual_s6 * transformidad_s6
        //FILA27
        //const gastos_benef_lh = 16683.73;
        val flujo_anual_s7 = estateDataModel.d_gf_Benef!! / 118
        var emergia_s7 = flujo_anual_s7 * transformidad_s7

        //FILA28
        val flujo_anual_s8 = estateDataModel.d_combustible!! * 39500000
        var emergia_s8 = flujo_anual_s8 * transformidad_s8
        //FILA29
        val flujo_anual_s9 = ((estateDataModel.d_val_infra!! / v_dolar2014) / 50) / 118
        var emergia_s9 = flujo_anual_s9 * transformidad_s9

        //FILA30
        val flujo_anual_s30 = ((estateDataModel.d_promedio_electrico!! * 12) / estateDataModel.l_Pdolar!!) / 118
        var emergia_s30 = flujo_anual_s30 * transformidad_s30

        //TOTAL
        var total_s = emergia_s1 + emergia_s2 + emergia_s3 + emergia_s4 + emergia_s5 + emergia_s6 +
                emergia_s7 + emergia_s8 + emergia_s9 + emergia_s30

        this.estateInfoModel.flujo_anual_s1 = flujo_anual_s1
        this.estateInfoModel.flujo_anual_s2 = flujo_anual_s2
        this.estateInfoModel.flujo_anual_s3 = flujo_anual_s3
        this.estateInfoModel.flujo_anual_s4 = flujo_anual_s4
        this.estateInfoModel.flujo_anual_s5 = flujo_anual_s5
        this.estateInfoModel.flujo_anual_s6 = flujo_anual_s6
        this.estateInfoModel.flujo_anual_s7 = flujo_anual_s7
        this.estateInfoModel.flujo_anual_s8 = flujo_anual_s8
        this.estateInfoModel.flujo_anual_s9 = flujo_anual_s9
        this.estateInfoModel.flujo_anual_s30 = flujo_anual_s30
        this.estateInfoModel.transformidad = transformidad
        this.estateInfoModel.transformidad_f2 = transformidad_f2
        this.estateInfoModel.transformidad_s3 = transformidad_s3
        this.estateInfoModel.transformidad_s4 = transformidad_s4
        this.estateInfoModel.transformidad_s5 = transformidad_s5
        this.estateInfoModel.transformidad_s6 = transformidad_s6
        this.estateInfoModel.transformidad_s7 = transformidad_s7
        this.estateInfoModel.transformidad_s8 = transformidad_s8
        this.estateInfoModel.transformidad_s9 = transformidad_s9
        this.estateInfoModel.transformidad_s30 = transformidad_s30
        this.energyModel.emergia_s1 = emergia_s1
        this.energyModel.emergia_s2 = emergia_s2
        this.energyModel.emergia_s3 = emergia_s3
        this.energyModel.emergia_s4 = emergia_s4
        this.energyModel.emergia_s5 = emergia_s5
        this.energyModel.emergia_s6 = emergia_s6
        this.energyModel.emergia_s7 = emergia_s7
        this.energyModel.emergia_s8 = emergia_s8
        this.energyModel.emergia_s9 = emergia_s9
        this.energyModel.emergia_s30 = emergia_s30
        this.estateInfoModel.total_s = total_s

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDryingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        editDrying()
        backDrying()
        return root
    }
    private fun editDrying() {
        val dryingId = arguments?.getString("dryingid")

        if (dryingId != null) {
            binding.btnsave.text = "Update"
            db.collection("drying").document(dryingId).get().addOnSuccessListener {
                // Toast.makeText(context, "Eliminado Correctamente", Toast.LENGTH_SHORT).show()
                Log.d("TAG-traerdatos", "editHarvest: "+ it)
                binding.etDYear.setText(it.get("d_year").toString())
                binding.etDValMaq.setText(it.get("d_val_maq").toString())
                binding.etPTranspiracion.setText(it.get("p_transpiracion").toString())
                binding.etDGfBenef.setText(it.get("d_gf_Benef").toString())
                binding.etDProduccion.setText(it.get("d_produccion").toString())
                binding.etDCombustible.setText(it.get("d_combustible").toString())
                binding.etPDensidad2.setText(it.get("p_densidad2").toString())
                binding.etPInsolation.setText(it.get("p_insolation").toString())
                binding.etDDiasSecado.setText(it.get("d_dias_secado").toString())
                binding.etDValInfra.setText(it.get("d_val_infra").toString())
                binding.etPEnegiaLib.setText(it.get("p_enegia_lib").toString())
                binding.etPAdmosfra.setText(it.get("p_admosfra").toString())
                binding.etDK.setText(it.get("d_k").toString())
                binding.etDPromedioElectrico.setText(it.get("d_promedio_electrico").toString())
                binding.etLPdolar.setText(it.get("l_Pdolar").toString())
                binding.etPDensidadA.setText(it.get("p_densidadA").toString())
                binding.etDPatioSecado.setText(it.get("d_patio_secado").toString())
                binding.etAreaFinca.setText(it.get("area_finca").toString())
                binding.etPAlbedo.setText(it.get("p_albedo").toString())
                binding.etPViento.setText(it.get("p_viento").toString())
            }
                .addOnFailureListener { e ->

                }
            binding.btnsave.setOnClickListener {
                initViews()
                val datosActualizados = hashMapOf(
                    "d_year" to d_year,
                    "d_val_maq" to d_val_maq.toDouble(),
                    "p_transpiracion" to p_transpiracion.toDouble(),
                    "d_gf_Benef" to d_gf_Benef.toDouble(),
                    "d_produccion" to d_produccion.toDouble(),
                    "d_combustible" to d_combustible.toDouble(),
                    "p_densidad2" to p_densidad2.toDouble(),
                    "p_insolation" to p_insolation.toDouble(),
                    "d_dias_secado" to d_dias_secado.toDouble(),
                    "d_val_infra" to d_val_infra.toDouble(),
                    "p_enegia_lib" to p_enegia_lib.toDouble(),
                    "p_admosfra" to p_admosfra.toDouble(),
                    "d_k" to d_k.toDouble(),
                    "d_promedio_electrico" to d_promedio_electrico.toDouble(),
                    "l_Pdolar" to l_Pdolar.toDouble(),
                    "p_densidadA" to p_densidadA.toDouble(),
                    "d_patio_secado" to d_patio_secado.toDouble(),
                    "area_finca" to area_finca.toDouble(),
                    "p_albedo" to p_albedo.toDouble(),
                    "p_viento" to p_viento.toDouble()

                )
                val datosActualizadosMap: Map<String, Any> = datosActualizados
                db.collection("drying").document(dryingId).update(datosActualizadosMap).addOnSuccessListener {
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
            saveDrying()
        }
    }
    private fun saveDrying(){
        binding.btnsave.setOnClickListener {
            val dryingCollection = db.collection("drying")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    initViews()
                    val estateId = arguments?.getString("estateId")
                    val drying = DryingModel("",d_year,d_produccion.toDouble(), d_dias_secado.toDouble(), d_k.toDouble(),
                        d_patio_secado.toDouble(),d_val_maq.toDouble(),d_gf_Benef.toDouble(),d_combustible.toDouble(),
                        d_val_infra.toDouble(),d_promedio_electrico.toDouble(),l_Pdolar.toDouble(),p_enegia_lib.toDouble(),
                        p_densidad2.toDouble(),p_viento.toDouble(),p_admosfra.toDouble(),p_densidadA.toDouble(),p_insolation.toDouble(),
                        p_albedo.toDouble(),p_transpiracion.toDouble(),area_finca.toDouble(),estateId)

                    val documentReference = dryingCollection.add(drying).await()
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
    private fun backDrying(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.botonBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dryingListFragment,bundle)
        }
    }
    private fun initViews() {
        d_year = binding.etDYear.text.toString()
        d_val_maq = binding.etDValMaq.text.toString()
        p_transpiracion = binding.etPTranspiracion.text.toString()
        d_gf_Benef = binding.etDGfBenef.text.toString()
        d_produccion = binding.etDProduccion.text.toString()
        d_combustible = binding.etDCombustible.text.toString()
        p_densidad2 = binding.etPDensidad2.text.toString()
        p_insolation = binding.etPInsolation.text.toString()
        d_dias_secado = binding.etDDiasSecado.text.toString()
        d_val_infra = binding.etDValInfra.text.toString()
        p_enegia_lib = binding.etPEnegiaLib.text.toString()
        p_admosfra = binding.etPAdmosfra.text.toString()
        d_k = binding.etDK.text.toString()
        d_promedio_electrico = binding.etDPromedioElectrico.text.toString()
        l_Pdolar = binding.etLPdolar.text.toString()
        p_densidadA = binding.etPDensidadA.text.toString()
        d_patio_secado = binding.etDPatioSecado.text.toString()
        area_finca = binding.etAreaFinca.text.toString()
        p_albedo = binding.etPAlbedo.text.toString()
        p_viento = binding.etPViento.text.toString()
    }

}