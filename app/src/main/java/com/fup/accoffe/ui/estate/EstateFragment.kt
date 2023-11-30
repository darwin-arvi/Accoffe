package com.fup.accoffe.ui.estate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fup.accoffe.databinding.FragmentEstateBinding

class EstateFragment : Fragment() {

    private var _binding: FragmentEstateBinding? = null

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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
/*
    //operaciones
    //PLANTACION FILA 1
    //esta formula es solar insolation de plantacion fila 1
    var insilacion_p_uno = estateData.p_promedio_inso * ins_m2
    var insolation_p_anio = insilacion_p_uno * estateData.p_duracion_cult
    var anio_f1_p = insolation_p_anio * (1 - estateData.p_albedo) * estateData.area_finca
    this.estateInfo.anio_f1_p = anio_f1_p

    var dos_anios_p_f1 = (anio_f1_p / 30) * 2

    this.estateInfo.dos_anios_p_f1 = dos_anios_p_f1

    var total_f1 = dos_anios_p_f1 + anio_f1_p

    this.estateInfo.total_f1 = total_f1

    val emergia_p_f1 = total_f1 * transformidad

    //PLANTACION FILA 2
    var anio_f2_p = estateData.p_capa_admos * estateData.area_finca *
            estateData.p_densidad_aire * estateData.p_vel_viento * (estateData.p_duracion_cult / 365)
    this.estateInfo.anio_f2_p = anio_f2_p
    //2 años iniciales
    var dos_anios_p_f2 = (anio_f2_p / 30) * 2
    this.estateInfo.dos_anios_p_f2 = dos_anios_p_f2

    var total_f2 = anio_f2_p + dos_anios_p_f2
    this.estateInfo.total_f2 = total_f2
    val emergia_p_f2 = total_f2 * transformidad_f2

    //PLANTACION F3
    //año analizado f3
    var evap_p = 3.3
    var evap_p_f3 = (evap_p * estateData.p_duracion_cult) / 1000
    var cropEt_p_f3 = evap_p_f3 * estateData.p_coefi_cult
    //año iniciales
    var anio_f3_p = estateData.area_finca * cropEt_p_f3 * estateData.p_energia_libre_gibbs * 1000
    this.estateInfo.anio_f3_p = anio_f3_p
    //2años analizados f3
    var dos_anios_p_f3 = (anio_f3_p / 30) * 2
    //total_f3
    var total_f3 = anio_f3_p + dos_anios_p_f3
    val emergia_p_f3 = total_f3 * transformidad_f3

    //PLANTACION F4
    //año analizado f4
    private var gravedad_f4 = 9.8

    //año anaalizado
    var anio_f4_p = estateData.area_finca * estateData.p_aspec_hidrico * estateData.p_agua *
            estateData.p_promedio_altura * estateData.p_densidad * gravedad_f4

    var dos_anios_p_f4 = (anio_f4_p / 30) * 2
    var total_f4 = anio_f4_p + dos_anios_p_f4
    val emergia_p_f4 = total_f4 * transformidad_f4

    //PLANTACION F5
    //año analizado f5
    var anio_f5_p = estateData.area_finca * estateData.p_transpiracion *
            estateData.p_densidad * estateData.p_energia_libre_gibbs
    var dos_anios_p_f5 = (anio_f5_p / 30) * 2
    var total_f5 = anio_f5_p + dos_anios_p_f5
    val emergia_p_f5 = total_f5 * transformidad_f5

    //PLANTACION F6
    private var kcal = 5400
    private var jkcal = 4186
    //año analizado f6
    private var anio_f6_p = estateData.area_finca * estateData.p_perdida_suelo *
    estateData.p_cont_materia_orga * kcal * jkcal
    //dos años analizados
    var dos_anios_p_f6 = (anio_f6_p / 30) * 2
    var total_f6 = anio_f6_p + dos_anios_p_f6
    val emergia_p_f6 = total_f6 * transformidad_f6

    //PLANTACION F7
    var dos_anios_p_f7 = (estateData.p_ferti_nitro / 30) * 2
    var total_f7 = dos_anios_p_f7 + estateData.p_ferti_nitro
    val emergia_p_f7 = total_f7 * transformidad_f7

    //PLANTACION F8
    var dos_anios_p_f8 = (estateData.p_ferti_fosfato / 30) * 2
    var total_f8 = estateData.p_ferti_fosfato + dos_anios_p_f8
    val emergia_p_f8 = total_f8 * transformidad_f8

    //PLANTACION F9
    var dos_anios_p_f9 = (estateData.p_ferti_potacio / 30) * 2
    var total_f9 = dos_anios_p_f9 + estateData.p_ferti_potacio
    val emergia_p_f9 = total_f9 * transformidad_f9

    //PLANTACION F10
    var dos_anios_p_f10 = (estateData.p_urea / 30) * 2
    var total_f10 = estateData.p_urea + dos_anios_p_f10
    val emergia_p_f10 = total_f10 * transformidad_f10

    //PLANTACION F11
    var dos_anios_p_f11 = (estateData.p_cal / 30) * 2
    var total_f11 = estateData.p_cal + dos_anios_p_f11
    val emergia_p_f11 = total_f11 * transformidad_f11

    //PLANTACION F12
    var dos_anios_p_f12 = (estateData.p_materia_orga) / 30
    var total_f12 = dos_anios_p_f12
    val emergia_p_f12 = total_f12 * transformidad_f12

    //PLANTACION F13
    var const2 = estateData.p_semillas * 3000 * 4186
    var dos_anios_p_f13 = (const2 / 30)
    var total_f13 = dos_anios_p_f13
    val emergia_p_f13 = total_f13 * transformidad_f13

    //PLANTACION F14
    var dos_anios_p_f14 = (estateData.p_maq_man / 30) * 2
    var total_f14 = estateData.p_maq_man + dos_anios_p_f14
    val emergia_p_f14 = total_f14 * transformidad_f14

    //PLANTACION F15
    //   val l_siembra_mante = 10000
    this.estateInfo.l_siembra_mante = estateData.l_siembra_mante

    //   this.estateInfo.l_siembra_mante = l_siembra_mante
    var dos_anios_p_f15 = (v_dolar2009 + v_dolar2010) / 30
    var total_f15 = dos_anios_p_f15 + estateData.l_siembra_mante
    val emergia_p_f15 = total_f15 * transformidad_f15

    //PLANTACION F16 l_siembra_mante
    var dos_anios_p_f16 = (estateData.p_pest_fung / 30) * 2
    var total_f16 = estateData.p_pest_fung + dos_anios_p_f16
    val emergia_p_f16 = total_f16 * transformidad_f16

    //TOTAL PLANTACION
    var total_P = emergia_p_f1 + emergia_p_f2 + emergia_p_f3 +
            emergia_p_f4 + emergia_p_f5 + emergia_p_f6 + emergia_p_f7 +
            emergia_p_f8 + emergia_p_f9 + emergia_p_f10 + emergia_p_f11 +
            emergia_p_f12 + emergia_p_f13 + emergia_p_f14 + emergia_p_f15 + emergia_p_f16

    this.estateInfo.total_p = total_P

    this.energia.emergia_p_f1 = emergia_p_f1
    this.energia.emergia_p_f2 = emergia_p_f2
    this.energia.emergia_p_f3 = emergia_p_f3
    this.energia.emergia_p_f4 = emergia_p_f4
    this.energia.emergia_p_f5 = emergia_p_f5
    this.energia.emergia_p_f6 = emergia_p_f6
    this.energia.emergia_p_f7 = emergia_p_f7
    this.energia.emergia_p_f8 = emergia_p_f8
    this.energia.emergia_p_f9 = emergia_p_f9
    this.energia.emergia_p_f10 = emergia_p_f10
    this.energia.emergia_p_f11 = emergia_p_f11
    this.energia.emergia_p_f12 = emergia_p_f12
    this.energia.emergia_p_f13 = emergia_p_f13
    this.energia.emergia_p_f14 = emergia_p_f14
    this.energia.emergia_p_f15 = emergia_p_f15
    this.energia.emergia_p_f16 = emergia_p_f16

    this.energia.s_year = estateData.p_year

    this.estateInfo.dos_anios_p_f3 = dos_anios_p_f3
    this.estateInfo.dos_anios_p_f4 = dos_anios_p_f4
    this.estateInfo.dos_anios_p_f5 = dos_anios_p_f5
    this.estateInfo.dos_anios_p_f6 = dos_anios_p_f6
    this.estateInfo.dos_anios_p_f7 = dos_anios_p_f7
    this.estateInfo.dos_anios_p_f8 = dos_anios_p_f8
    this.estateInfo.dos_anios_p_f9 = dos_anios_p_f9
    this.estateInfo.dos_anios_p_f10 = dos_anios_p_f10
    this.estateInfo.dos_anios_p_f11 = dos_anios_p_f11
    this.estateInfo.dos_anios_p_f12 = dos_anios_p_f12
    this.estateInfo.dos_anios_p_f13 = dos_anios_p_f13
    this.estateInfo.dos_anios_p_f14 = dos_anios_p_f14
    this.estateInfo.dos_anios_p_f15 = dos_anios_p_f15
    this.estateInfo.dos_anios_p_f16 = dos_anios_p_f16

    this.estateInfo.anio_f3_p = anio_f3_p
    this.estateInfo.anio_f4_p = anio_f4_p
    this.estateInfo.anio_f5_p = anio_f5_p
    this.estateInfo.anio_f6_p = anio_f6_p

    this.estateInfo.total_f3 = total_f3
    this.estateInfo.total_f4 = total_f4
    this.estateInfo.total_f5 = total_f5
    this.estateInfo.total_f6 = total_f6
    this.estateInfo.total_f7 = total_f7
    this.estateInfo.total_f8 = total_f8
    this.estateInfo.total_f9 = total_f9
    this.estateInfo.total_f10 = total_f10
    this.estateInfo.total_f11 = total_f11
    this.estateInfo.total_f12 = total_f12
    this.estateInfo.total_f13 = total_f13
    this.estateInfo.total_f14 = total_f14
    this.estateInfo.total_f15 = total_f15
    this.estateInfo.total_f16 = total_f16
},
async guardar() {
    alert("Saving")
    private var addedDoc = await addDoc(sumatoriaoneColl, this.energia)
    console.log(addedDoc)
    this.$router.go(-1)
}
},
created() {
    val infoId = this.$route.params.infoId
    this.infoId = infoId
    this.getEstate()
},
    */
}