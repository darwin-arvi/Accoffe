package com.fup.accoffe.ui.drying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentDryingBinding

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


    private var transformidad = 1
    private var transformidad_f2 = 2520
    private var transformidad_s3 = 145000
    private var transformidad_s4 = 86000
    private var transformidad_s5 = 2420000000
    private var transformidad_s6 = 26500000000000
    private var transformidad_s7 = 22500000000000
    private var transformidad_s8 = 111111
    private var transformidad_s9 = 26500000000000
    private var transformidad_s30 = 26500000000000
    private var v_dolar2014 = 2000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDryingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        return root
    }

    private fun initViews() {
        d_year = binding.etDYear.text.toString()
        d_val_maq = binding.etDValMaq.text.toString()
        p_transpiracion = binding.etDYear.text.toString()
        d_gf_Benef = binding.etDYear.text.toString()
        d_produccion = binding.etDYear.text.toString()
        d_combustible = binding.etDYear.text.toString()
        p_densidad2 = binding.etDYear.text.toString()
        p_insolation = binding.etDYear.text.toString()
        d_dias_secado = binding.etDYear.text.toString()
        d_val_infra = binding.etDYear.text.toString()
        p_enegia_lib = binding.etDYear.text.toString()
        p_admosfra = binding.etDYear.text.toString()
        d_k = binding.etDYear.text.toString()
        d_promedio_electrico = binding.etDYear.text.toString()
        l_Pdolar = binding.etDYear.text.toString()
        p_densidadA = binding.etDYear.text.toString()
        d_patio_secado = binding.etDYear.text.toString()
        area_finca = binding.etDYear.text.toString()
        p_albedo = binding.etDYear.text.toString()
        p_viento = binding.etDYear.text.toString()
    }


    //operaciones

    //FILA21
    /*
    private var insolacion_s = estateData.p_insolation * 3600000
    private var insolacion_diez = insolacion_s * 10
    private var flujo_anual_s1 = insolacion_diez * (1 - estateData.p_albedo) * estateData.area_finca

    val emergia_s1 = flujo_anual_s1 * transformidad
/*
    //FILA22
    private var flujo_anual_s2 = estateData.area_finca * estateData.p_admosfra * estateData.p_densidadA *
    estateData.p_viento
    val emergia_s2 = flujo_anual_s2 * transformidad_f2

    //FILA23
    private var area_finca_1 = 10;//////estateData.area_finca
    private var evapo_energ = area_finca_1 * estateData.p_transpiracion * estateData.p_densidad2 *
    estateData.p_enegia_lib
    private var flujo_anual_s3 = (evapo_energ / 365) * 10
    val emergia_s3 = flujo_anual_s3 * transformidad_s3

    //FILA24
    private var prod_perg = estateData.d_produccion / 5
    private var prod_anio = (prod_perg * 2) / 1000
    //const water_cons = prod_anio / 1000
    private var flujo_anual_s4 = prod_anio * estateData.d_k * estateData.p_enegia_lib
    val emergia_s4 = flujo_anual_s4 * transformidad_s4

    //FILA25
    private var flujo_anual_s5 = estateData.d_patio_secado * 1000
    val emergia_s5 = flujo_anual_s5 * transformidad_s5

    //FILA26
    private var flujo_anual_s6 = ((estateData.d_val_maq / v_dolar2014) / 30) / 118
    val emergia_s6 = flujo_anual_s6 * transformidad_s6
    //FILA27
    //private var gastos_benef_lh = 16683.73
    private var flujo_anual_s7 = estateData.d_gf_Benef / 118
    val emergia_s7 = flujo_anual_s7 * transformidad_s7

    //FILA28
    private var flujo_anual_s8 = estateData.d_combustible * 39500000
    val emergia_s8 = flujo_anual_s8 * transformidad_s8
    //FILA29
    private var flujo_anual_s9 = ((estateData.d_val_infra / v_dolar2014) / 50) / 118
    val emergia_s9 = flujo_anual_s9 * transformidad_s9

    //FILA30
    private var flujo_anual_s30 = ((estateData.d_promedio_electrico * 12) / estateData.l_Pdolar) / 118
    val emergia_s30 = flujo_anual_s30 * transformidad_s30
*/
    //TOTAL
   /* var total_s = emergia_s1 + emergia_s2 + emergia_s3 + emergia_s4 + emergia_s5 + emergia_s6 +
            emergia_s7 + emergia_s8 + emergia_s9 + emergia_s30

    this.estateInfo.flujo_anual_s1 = flujo_anual_s1
    this.estateInfo.flujo_anual_s2 = flujo_anual_s2
    this.estateInfo.flujo_anual_s3 = flujo_anual_s3
    this.estateInfo.flujo_anual_s4 = flujo_anual_s4
    this.estateInfo.flujo_anual_s5 = flujo_anual_s5
    this.estateInfo.flujo_anual_s6 = flujo_anual_s6
    this.estateInfo.flujo_anual_s7 = flujo_anual_s7
    this.estateInfo.flujo_anual_s8 = flujo_anual_s8
    this.estateInfo.flujo_anual_s9 = flujo_anual_s9
    this.estateInfo.flujo_anual_s30 = flujo_anual_s30

    this.estateInfo.transformidad = transformidad
    this.estateInfo.transformidad_f2 = transformidad_f2
    this.estateInfo.transformidad_s3 = transformidad_s3
    this.estateInfo.transformidad_s4 = transformidad_s4
    this.estateInfo.transformidad_s5 = transformidad_s5
    this.estateInfo.transformidad_s6 = transformidad_s6
    this.estateInfo.transformidad_s7 = transformidad_s7
    this.estateInfo.transformidad_s8 = transformidad_s8
    this.estateInfo.transformidad_s9 = transformidad_s9
    this.estateInfo.transformidad_s30 = transformidad_s30

    this.estateInfo.emergia_s1 = emergia_s1
    this.estateInfo.emergia_s2 = emergia_s2
    this.estateInfo.emergia_s3 = emergia_s3
    this.estateInfo.emergia_s4 = emergia_s4
    this.estateInfo.emergia_s5 = emergia_s5
    this.estateInfo.emergia_s6 = emergia_s6
    this.estateInfo.emergia_s7 = emergia_s7
    this.estateInfo.emergia_s8 = emergia_s8
    this.estateInfo.emergia_s9 = emergia_s9
    this.estateInfo.emergia_s30 = emergia_s30

    this.estateInfo.total_s = total_s
},
     */
async guardar() {
    val plantSnapShot = await db.collection('sumatoriaone').where('s_year', '==', this.estateInfo.d_year).get()
    val plants = []
    plantSnapShot.forEach((plant) => {
        val plantData = plant.data()
        plantData.id = plant.id
        plants.push(plantData)
    });*/
    /*
    this.plants = plants
    val id = plants[0].id

    val sumaRef = doc(sumatoriaoneColl, id)
    this.docRefe = sumaRef
    val suma = await getDoc(this.docRefe)
    val sumaData = suma.data()

    this.energia.emergia_p_f1 = sumaData.emergia_p_f1
    this.energia.emergia_p_f2 = sumaData.emergia_p_f2
    this.energia.emergia_p_f3 = sumaData.emergia_p_f3
    this.energia.emergia_p_f4 = sumaData.emergia_p_f4
    this.energia.emergia_p_f5 = sumaData.emergia_p_f5
    this.energia.emergia_p_f6 = sumaData.emergia_p_f6
    this.energia.emergia_p_f7 = sumaData.emergia_p_f7
    this.energia.emergia_p_f8 = sumaData.emergia_p_f8
    this.energia.emergia_p_f9 = sumaData.emergia_p_f9
    this.energia.emergia_p_f10 = sumaData.emergia_p_f10
    this.energia.emergia_p_f11 = sumaData.emergia_p_f11
    this.energia.emergia_p_f12 = sumaData.emergia_p_f12
    this.energia.emergia_p_f13 = sumaData.emergia_p_f13
    this.energia.emergia_p_f14 = sumaData.emergia_p_f14
    this.energia.emergia_p_f15 = sumaData.emergia_p_f15
    this.energia.emergia_p_f16 = sumaData.emergia_p_f16
    this.energia.emergia_p_f17 = sumaData.emergia_p_f17
    this.energia.emergia_p_f18 = sumaData.emergia_p_f18
    this.energia.emergia_p_f19 = sumaData.emergia_p_f19
    this.energia.emergia_p_f20 = sumaData.emergia_p_f20

    this.energia.s_year = sumaData.s_year

    this.energia.emergia_s1 = this.estateInfo.emergia_s1
    this.energia.emergia_s2 = this.estateInfo.emergia_s2
    this.energia.emergia_s3 = this.estateInfo.emergia_s3
    this.energia.emergia_s4 = this.estateInfo.emergia_s4
    this.energia.emergia_s5 = this.estateInfo.emergia_s5
    this.energia.emergia_s6 = this.estateInfo.emergia_s6
    this.energia.emergia_s7 = this.estateInfo.emergia_s7
    this.energia.emergia_s8 = this.estateInfo.emergia_s8
    this.energia.emergia_s9 = this.estateInfo.emergia_s9
    this.energia.emergia_s30 = this.estateInfo.emergia_s30

    this.update()
    alert("Saving")

},
*/
/*
async update() {
    await setDoc(this.docRefe, this.energia)
    this.$router.go(-1)
}

},*/
/*created() {
    val infoId = this.$route.params.infoId
    this.infoId = infoId
    this.getEstate()
}, */

}