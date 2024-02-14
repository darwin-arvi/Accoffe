package com.fup.accoffe.ui.drying

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentDryingBinding
import com.fup.accoffe.databinding.FragmentDryingInfoBinding
import com.fup.accoffe.databinding.FragmentHarvestInfoBinding
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DryingInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DryingInfoFragment : Fragment() {
    private var _binding: FragmentDryingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance()

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
        arguments?.let {
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDryingInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        backDryingInfo()
        energyModel = EnergyModel()
        getStateData()

        return root
    }
    private fun backDryingInfo() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }
    private fun getStateData(){

       try {
           val dryingId = arguments?.getString("dryingid")
           Log.d("getStateData", "Received estateId: $dryingId")

           db.collection("drying").document(dryingId!!).get()
               .addOnSuccessListener { documentSnapshot ->
                   if (documentSnapshot.exists()) {

                       val info = documentSnapshot.toObject(EstateDataModel::class.java)
                       if (info != null) {
                           estateDataModel = info

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

                           binding.item12.text=estateInfoModel.flujo_anual_s1.toString()
                           binding.item13.text =estateInfoModel.transformidad.toString()
                           binding.item14.text =energyModel.emergia_s1.toString()

                           binding.item22.text=estateInfoModel.flujo_anual_s2.toString()
                           binding.item23.text =estateInfoModel.transformidad_f2.toString()
                           binding.item24.text =energyModel.emergia_s2.toString()

                           binding.item32.text=estateInfoModel.flujo_anual_s3.toString()
                           binding.item33.text =estateInfoModel.transformidad_s3.toString()
                           binding.item34.text =energyModel.emergia_s3.toString()

                           binding.item42.text=estateInfoModel.flujo_anual_s4.toString()
                           binding.item43.text =estateInfoModel.transformidad_s4.toString()
                           binding.item44.text =energyModel.emergia_s4.toString()

                           binding.item52.text=estateInfoModel.flujo_anual_s5.toString()
                           binding.item53.text =estateInfoModel.transformidad_s5.toString()
                           binding.item54.text =energyModel.emergia_s5.toString()

                           binding.item62.text=estateInfoModel.flujo_anual_s6.toString()
                           binding.item63.text =estateInfoModel.transformidad_s6.toString()
                           binding.item64.text =energyModel.emergia_s6.toString()

                           binding.item72.text=estateInfoModel.flujo_anual_s7.toString()
                           binding.item73.text =estateInfoModel.transformidad_s7.toString()
                           binding.item74.text =energyModel.emergia_s7.toString()

                           binding.item82.text=estateInfoModel.flujo_anual_s8.toString()
                           binding.item83.text =estateInfoModel.transformidad_s8.toString()
                           binding.item84.text =energyModel.emergia_s8.toString()

                           binding.item92.text=estateInfoModel.flujo_anual_s9.toString()
                           binding.item93.text =estateInfoModel.transformidad_s9.toString()
                           binding.item94.text =energyModel.emergia_s9.toString()

                           binding.item02.text=estateInfoModel.flujo_anual_s30.toString()
                           binding.item03.text =estateInfoModel.transformidad_s30.toString()
                           binding.item04.text =energyModel.emergia_s30.toString()

                           binding.item112.text =estateInfoModel.total_s.toString()

                       }


                       Log.d("TAG", "Document data: ${estateDataModel}")
                   } else {
                       Log.d("TAG", "Document does not exist")
                   }
               }
               .addOnFailureListener { e ->
                   Log.e("TAG", "Error getting document", e)
               }


       } catch (e: Exception) {
           println("Errpr get estateData: $e")
       }
    }

}
