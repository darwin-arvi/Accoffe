package com.fup.accoffe.ui.plantation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentDryingInfoBinding
import com.fup.accoffe.databinding.FragmentPlantationInfoBinding
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
 * Use the [PlantationInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlantationInfoFragment : Fragment() {
    private var _binding: FragmentPlantationInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance()
    private var transformidad = 1.0
    private var transformidad_f2 = 2520.0
    private var transformidad_f3 = 30600.0
    private var transformidad_f4 = 17600.0
    private var transformidad_f5 = 39800.0
    private var transformidad_f6 = 74000.0
    private var transformidad_f7 = 6620000000.0
    private var transformidad_f8 = 9350000000.0
    private var transformidad_f9 = 932000000.0
    private var transformidad_f10 = 6620000000.0
    private var transformidad_f11 = 1680000000.0
    private var transformidad_f12 = 3870000000.0
    private var transformidad_f13 = 58500.0
    private var transformidad_f14 = 6700000000.0
    private var transformidad_f15 = 22500000000000.0
    private var transformidad_f16 = 14800000000.0
    private var ins_m2 = 3600000.0

    private var v_dolar2010 = 337.18
    private var v_dolar2009 = 580.59
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantationInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        backPlantationInfo()
        energyModel = EnergyModel()
        getStateData()

        return root
    }
    private fun backPlantationInfo() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }
    private fun getStateData(){

        val plantationId = arguments?.getString("plantationid")
        Log.d("getStateData", "Received estateId: $plantationId")

        db.collection("planting").document(plantationId!!).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {

                    val info = documentSnapshot.toObject(EstateDataModel::class.java)
                    if (info != null) {
                        estateDataModel = info

                        estateInfoModel.transformidad=transformidad
                        estateInfoModel.transformidad_f2=transformidad_f2
                        estateInfoModel.transformidad_f3=transformidad_f3
                        estateInfoModel.transformidad_f4=transformidad_f4
                        estateInfoModel.transformidad_f5=transformidad_f5
                        estateInfoModel.transformidad_f6=transformidad_f6
                        estateInfoModel.transformidad_f7=transformidad_f7
                        estateInfoModel.transformidad_f8=transformidad_f8
                        estateInfoModel.transformidad_f9=transformidad_f9
                        estateInfoModel.transformidad_f10=transformidad_f10
                        estateInfoModel.transformidad_f11=transformidad_f11
                        estateInfoModel.transformidad_f12=transformidad_f12
                        estateInfoModel.transformidad_f13=transformidad_f13
                        estateInfoModel.transformidad_f14=transformidad_f14
                        estateInfoModel.transformidad_f15=transformidad_f15
                        estateInfoModel.transformidad_f16=transformidad_f16


                        estateInfoModel.p_year = estateDataModel.p_year
                        estateInfoModel.p_albedo = estateDataModel.p_albedo
                        estateInfoModel.p_promedio_inso = estateDataModel.p_promedio_inso
                        estateInfoModel.p_capa_admos = estateDataModel.p_capa_admos
                        estateInfoModel.p_densidad_aire = estateDataModel.p_densidad_aire
                        estateInfoModel.p_vel_viento = estateDataModel.p_vel_viento
                        estateInfoModel.p_duracion_cult = estateDataModel.p_duracion_cult
                        estateInfoModel.p_evaporacion_poten = estateDataModel.p_evaporacion_poten
                        estateInfoModel.p_coefi_cult = estateDataModel.p_coefi_cult
                        estateInfoModel.p_energia_libre_gibbs = estateDataModel.p_energia_libre_gibbs
                        estateInfoModel.p_aspec_hidrico = estateDataModel.p_aspec_hidrico
                        estateInfoModel.p_agua = estateDataModel.p_agua
                        estateInfoModel.p_promedio_altura = estateDataModel.p_promedio_altura
                        estateInfoModel.p_densidad = estateDataModel.p_densidad
                        estateInfoModel.p_transpiracion = estateDataModel.p_transpiracion
                        estateInfoModel.p_perdida_suelo = estateDataModel.p_perdida_suelo
                        estateInfoModel.p_cont_materia_orga = estateDataModel.p_materia_orga
                        estateInfoModel.p_ferti_nitro = estateDataModel.p_ferti_nitro
                        estateInfoModel.p_ferti_fosfato = estateDataModel.p_ferti_fosfato
                        estateInfoModel.p_ferti_potacio = estateDataModel.p_ferti_potacio
                        estateInfoModel.p_urea = estateDataModel.p_urea
                        estateInfoModel.p_cal = estateDataModel.p_cal
                        estateInfoModel.p_materia_orga = estateDataModel.p_materia_orga
                        estateInfoModel.p_semillas = estateDataModel.p_semillas
                        estateInfoModel.p_maq_man = estateDataModel.p_maq_man
                        estateInfoModel.p_pest_fung = estateDataModel.p_pest_fung
                        estateInfoModel.area_finca = estateDataModel.area_finca



                        //operaciones
                        //PLANTACION FILA 1
                        //esta formula es solar insolation de plantacion fila 1
                        var insilacion_p_uno = estateDataModel.p_promedio_inso!!*ins_m2
                        var insolation_p_anio = insilacion_p_uno!!* estateDataModel.p_duracion_cult!!
                        var anio_f1_p = insolation_p_anio!! * (1 - estateDataModel.p_albedo!!) * estateDataModel.area_finca!!
                        this.estateInfoModel.anio_f1_p = anio_f1_p;

                        var dos_anios_p_f1 = (anio_f1_p / 30) * 2;

                        this.estateInfoModel.dos_anios_p_f1 = dos_anios_p_f1;

                        var total_f1 = dos_anios_p_f1 + anio_f1_p;

                        this.estateInfoModel.total_f1 = total_f1;

                        var emergia_p_f1 = total_f1 * transformidad

                        //PLANTACION FILA 2
                        var anio_f2_p = estateDataModel.p_capa_admos!! * estateDataModel.area_finca!! *
                                estateDataModel.p_densidad_aire!! * estateDataModel.p_vel_viento!! * (estateDataModel.p_duracion_cult!! / 365);
                        this.estateInfoModel.anio_f2_p = anio_f2_p;
                        //2 años iniciales
                        var dos_anios_p_f2 = (anio_f2_p / 30) * 2;
                        this.estateInfoModel.dos_anios_p_f2 = dos_anios_p_f2;

                        var total_f2 = anio_f2_p + dos_anios_p_f2;
                        this.estateInfoModel.total_f2 = total_f2;
                        var emergia_p_f2 = total_f2 * transformidad_f2;

                        //PLANTACION F3
                        //año analizado f3
                        var evap_p = 3.3;
                        var evap_p_f3 = (evap_p * estateDataModel.p_duracion_cult!!) / 1000;
                        var cropEt_p_f3 = evap_p_f3 * estateDataModel.p_coefi_cult!!
                        //año iniciales
                        var anio_f3_p = estateDataModel.area_finca!! * cropEt_p_f3 * estateDataModel.p_energia_libre_gibbs!! * 1000;
                        this.estateInfoModel.anio_f3_p = anio_f3_p
                        //2años analizados f3
                        var dos_anios_p_f3 = (anio_f3_p / 30) * 2
                        //total_f3
                        var total_f3 = anio_f3_p + dos_anios_p_f3
                        var emergia_p_f3 = total_f3 * transformidad_f3

                        //PLANTACION F4
                        //año analizado f4
                        val gravedad_f4 = 9.8

                        //año anaalizado
                        var anio_f4_p = estateDataModel.area_finca!! * estateDataModel.p_aspec_hidrico!! * estateDataModel.p_agua!! *
                                estateDataModel.p_promedio_altura!! * estateDataModel.p_densidad!! * gravedad_f4;

                        var dos_anios_p_f4 = (anio_f4_p / 30) * 2;
                        var total_f4 = anio_f4_p + dos_anios_p_f4;
                        var emergia_p_f4 = total_f4 * transformidad_f4

                        //PLANTACION F5
                        //año analizado f5
                        val anio_f5_p = estateDataModel.area_finca!! * estateDataModel.p_transpiracion!! *
                                estateDataModel.p_densidad!! * estateDataModel.p_energia_libre_gibbs!!
                        var dos_anios_p_f5 = (anio_f5_p / 30) * 2;
                        var total_f5 = anio_f5_p + dos_anios_p_f5;
                        var emergia_p_f5 = total_f5 * transformidad_f5;

                        //PLANTACION F6
                        val kcal = 5400;
                        val jkcal = 4186;
                        //año analizado f6
                        val anio_f6_p = estateDataModel.area_finca!! * estateDataModel.p_perdida_suelo!! *
                                estateDataModel.p_cont_materia_orga!! * kcal * jkcal;
                        //dos años analizados
                        var dos_anios_p_f6 = (anio_f6_p / 30) * 2;
                        var total_f6 = anio_f6_p + dos_anios_p_f6;
                        var emergia_p_f6 = total_f6 * transformidad_f6;

                        //PLANTACION F7
                        var dos_anios_p_f7 = (estateDataModel.p_ferti_nitro!! / 30) * 2;
                        var total_f7 = dos_anios_p_f7 + estateDataModel.p_ferti_nitro!!;
                        var emergia_p_f7 = total_f7 * transformidad_f7;

                        //PLANTACION F8
                        var dos_anios_p_f8 = (estateDataModel.p_ferti_fosfato!! / 30) * 2;
                        var total_f8 = estateDataModel.p_ferti_fosfato!! + dos_anios_p_f8;
                        var emergia_p_f8 = total_f8 * transformidad_f8;

                        //PLANTACION F9
                        var dos_anios_p_f9 = (estateDataModel.p_ferti_potacio!! / 30) * 2;
                        var total_f9 = dos_anios_p_f9 + estateDataModel.p_ferti_potacio!!;
                        var emergia_p_f9 = total_f9 * transformidad_f9;

                        //PLANTACION F10
                        var dos_anios_p_f10 = (estateDataModel.p_urea!! / 30) * 2;
                        var total_f10 = estateDataModel.p_urea!! + dos_anios_p_f10;
                        var emergia_p_f10 = total_f10 * transformidad_f10;

                        //PLANTACION F11
                        var dos_anios_p_f11 = (estateDataModel.p_cal!! / 30) * 2;
                        var total_f11 = estateDataModel.p_cal!! + dos_anios_p_f11;
                        var emergia_p_f11 = total_f11 * transformidad_f11;

                        //PLANTACION F12
                        var dos_anios_p_f12 = (estateDataModel.p_materia_orga!!) / 30;
                        var total_f12 = dos_anios_p_f12;
                        var emergia_p_f12 = total_f12 * transformidad_f12;

                        //PLANTACION F13
                        var const2 = estateDataModel.p_semillas!! * 3000 * 4186;
                        var dos_anios_p_f13 = (const2 / 30);
                        var total_f13 = dos_anios_p_f13;
                        var emergia_p_f13 = total_f13 * transformidad_f13;

                        //PLANTACION F14
                        var dos_anios_p_f14 = (estateDataModel.p_maq_man!! / 30) * 2;
                        var total_f14 = estateDataModel.p_maq_man!! + dos_anios_p_f14;
                        var emergia_p_f14 = total_f14 * transformidad_f14;

                        //PLANTACION F15
                        //   let l_siembra_mante = 10000;
                        this.estateInfoModel.l_siembra_mante = estateDataModel.l_siembra_mante;

                        //   this.estateInfo.l_siembra_mante = l_siembra_mante;
                        var dos_anios_p_f15 = (v_dolar2009 + v_dolar2010) / 30;
                        var total_f15 = dos_anios_p_f15 + estateDataModel.l_siembra_mante!!;
                        var emergia_p_f15 = total_f15 * transformidad_f15;

                        //PLANTACION F16 l_siembra_mante
                        var dos_anios_p_f16 = (estateDataModel.p_pest_fung!! / 30) * 2;
                        var total_f16 = estateDataModel.p_pest_fung!! + dos_anios_p_f16;
                        var emergia_p_f16 = total_f16 * transformidad_f16;

                        //TOTAL PLANTACION
                        var total_P = emergia_p_f1 + emergia_p_f2 + emergia_p_f3 +
                                emergia_p_f4 + emergia_p_f5 + emergia_p_f6 + emergia_p_f7 +
                                emergia_p_f8 + emergia_p_f9 + emergia_p_f10 + emergia_p_f11 +
                                emergia_p_f12 + emergia_p_f13 + emergia_p_f14 + emergia_p_f15 + emergia_p_f16;

                        this.estateInfoModel.total_p = total_P;

                        this.energyModel.emergia_p_f1 = emergia_p_f1;
                        this.energyModel.emergia_p_f2 = emergia_p_f2;
                        this.energyModel.emergia_p_f3 = emergia_p_f3;
                        this.energyModel.emergia_p_f4 = emergia_p_f4;
                        this.energyModel.emergia_p_f5 = emergia_p_f5;
                        this.energyModel.emergia_p_f6 = emergia_p_f6;
                        this.energyModel.emergia_p_f7 = emergia_p_f7;
                        this.energyModel.emergia_p_f8 = emergia_p_f8;
                        this.energyModel.emergia_p_f9 = emergia_p_f9;
                        this.energyModel.emergia_p_f10 = emergia_p_f10;
                        this.energyModel.emergia_p_f11 = emergia_p_f11;
                        this.energyModel.emergia_p_f12 = emergia_p_f12;
                        this.energyModel.emergia_p_f13 = emergia_p_f13;
                        this.energyModel.emergia_p_f14 = emergia_p_f14;
                        this.energyModel.emergia_p_f15 = emergia_p_f15;
                        this.energyModel.emergia_p_f16 = emergia_p_f16;

                        this.energyModel.s_year = estateDataModel.p_year;

                        this.estateInfoModel.dos_anios_p_f3 = dos_anios_p_f3;
                        this.estateInfoModel.dos_anios_p_f4 = dos_anios_p_f4;
                        this.estateInfoModel.dos_anios_p_f5 = dos_anios_p_f5;
                        this.estateInfoModel.dos_anios_p_f6 = dos_anios_p_f6;
                        this.estateInfoModel.dos_anios_p_f7 = dos_anios_p_f7;
                        this.estateInfoModel.dos_anios_p_f8 = dos_anios_p_f8;
                        this.estateInfoModel.dos_anios_p_f9 = dos_anios_p_f9;
                        this.estateInfoModel.dos_anios_p_f10 = dos_anios_p_f10;
                        this.estateInfoModel.dos_anios_p_f11 = dos_anios_p_f11;
                        this.estateInfoModel.dos_anios_p_f12 = dos_anios_p_f12;
                        this.estateInfoModel.dos_anios_p_f13 = dos_anios_p_f13;
                        this.estateInfoModel.dos_anios_p_f14 = dos_anios_p_f14;
                        this.estateInfoModel.dos_anios_p_f15 = dos_anios_p_f15;
                        this.estateInfoModel.dos_anios_p_f16 = dos_anios_p_f16;

                        this.estateInfoModel.anio_f3_p = anio_f3_p;
                        this.estateInfoModel.anio_f4_p = anio_f4_p;
                        this.estateInfoModel.anio_f5_p = anio_f5_p;
                        this.estateInfoModel.anio_f6_p = anio_f6_p;

                        this.estateInfoModel.total_f3 = total_f3;
                        this.estateInfoModel.total_f4 = total_f4;
                        this.estateInfoModel.total_f5 = total_f5;
                        this.estateInfoModel.total_f6 = total_f6;
                        this.estateInfoModel.total_f7 = total_f7;
                        this.estateInfoModel.total_f8 = total_f8;
                        this.estateInfoModel.total_f9 = total_f9;
                        this.estateInfoModel.total_f10 = total_f10;
                        this.estateInfoModel.total_f11 = total_f11;
                        this.estateInfoModel.total_f12 = total_f12;
                        this.estateInfoModel.total_f13 = total_f13;
                        this.estateInfoModel.total_f14 = total_f14;
                        this.estateInfoModel.total_f15 = total_f15;
                        this.estateInfoModel.total_f16 = total_f16;

                        binding.item12.text=estateInfoModel.dos_anios_p_f1.toString()
                        binding.item13.text =estateInfoModel.anio_f1_p.toString()
                        binding.item14.text=estateInfoModel.total_f1.toString()
                        binding.item15.text =estateInfoModel.transformidad.toString()
                        binding.item16.text =energyModel.emergia_p_f1.toString()

                        binding.item22.text=estateInfoModel.dos_anios_p_f2.toString()
                        binding.item23.text =estateInfoModel.anio_f2_p.toString()
                        binding.item24.text=estateInfoModel.total_f2.toString()
                        binding.item25.text =estateInfoModel.transformidad_f2.toString()
                        binding.item26.text =energyModel.emergia_p_f2.toString()

                        binding.item32.text=estateInfoModel.dos_anios_p_f3.toString()
                        binding.item33.text =estateInfoModel.anio_f3_p.toString()
                        binding.item34.text=estateInfoModel.total_f3.toString()
                        binding.item35.text =estateInfoModel.transformidad_f3.toString()
                        binding.item36.text =energyModel.emergia_p_f3.toString()

                        binding.item42.text=estateInfoModel.dos_anios_p_f4.toString()
                        binding.item43.text =estateInfoModel.anio_f4_p.toString()
                        binding.item44.text=estateInfoModel.total_f4.toString()
                        binding.item45.text =estateInfoModel.transformidad_f4.toString()
                        binding.item46.text =energyModel.emergia_p_f4.toString()

                        binding.item52.text=estateInfoModel.dos_anios_p_f5.toString()
                        binding.item53.text =estateInfoModel.anio_f5_p.toString()
                        binding.item54.text=estateInfoModel.total_f5.toString()
                        binding.item55.text =estateInfoModel.transformidad_f5.toString()
                        binding.item56.text =energyModel.emergia_p_f5.toString()

                        binding.item62.text=estateInfoModel.dos_anios_p_f6.toString()
                        binding.item63.text =estateInfoModel.anio_f6_p.toString()
                        binding.item64.text=estateInfoModel.total_f6.toString()
                        binding.item65.text =estateInfoModel.transformidad_f6.toString()
                        binding.item66.text =energyModel.emergia_p_f6.toString()

                        binding.item72.text=estateInfoModel.dos_anios_p_f7.toString()
                        binding.item73.text =estateInfoModel.p_ferti_nitro.toString()
                        binding.item74.text=estateInfoModel.total_f7.toString()
                        binding.item75.text =estateInfoModel.transformidad_f7.toString()
                        binding.item76.text =energyModel.emergia_p_f7.toString()

                        binding.item82.text=estateInfoModel.dos_anios_p_f8.toString()
                        binding.item83.text =estateInfoModel.p_ferti_fosfato.toString()
                        binding.item84.text=estateInfoModel.total_f8.toString()
                        binding.item85.text =estateInfoModel.transformidad_f8.toString()
                        binding.item86.text =energyModel.emergia_p_f8.toString()

                        binding.item92.text=estateInfoModel.dos_anios_p_f9.toString()
                        binding.item93.text =estateInfoModel.p_ferti_potacio.toString()
                        binding.item94.text=estateInfoModel.total_f9.toString()
                        binding.item95.text =estateInfoModel.transformidad_f9.toString()
                        binding.item96.text =energyModel.emergia_p_f9.toString()

                        binding.item02.text=estateInfoModel.dos_anios_p_f10.toString()
                        binding.item03.text =estateInfoModel.p_urea.toString()
                        binding.item04.text=estateInfoModel.total_f10.toString()
                        binding.item05.text =estateInfoModel.transformidad_f10.toString()
                        binding.item06.text =energyModel.emergia_p_f10.toString()

                        binding.item1112.text=estateInfoModel.dos_anios_p_f11.toString()
                        binding.item1113.text =estateInfoModel.p_cal.toString()
                        binding.item1114.text=estateInfoModel.total_f11.toString()
                        binding.item1115.text =estateInfoModel.transformidad_f11.toString()
                        binding.item1116.text =energyModel.emergia_p_f11.toString()

                        binding.item2222.text=estateInfoModel.dos_anios_p_f12.toString()
                        binding.item2223.text ="0.0"
                        binding.item2224.text=estateInfoModel.total_f12.toString()
                        binding.item2225.text =estateInfoModel.transformidad_f12.toString()
                        binding.item2226.text =energyModel.emergia_p_f12.toString()

                        binding.item3332.text=estateInfoModel.dos_anios_p_f13.toString()
                        binding.item3333.text ="0.0"
                        binding.item3334.text=estateInfoModel.total_f13.toString()
                        binding.item3335.text =estateInfoModel.transformidad_f13.toString()
                        binding.item3336.text =energyModel.emergia_p_f13.toString()

                        binding.item4442.text=estateInfoModel.dos_anios_p_f14.toString()
                        binding.item4443.text =estateInfoModel.p_maq_man.toString()
                        binding.item4444.text=estateInfoModel.total_f14.toString()
                        binding.item4445.text =estateInfoModel.transformidad_f14.toString()
                        binding.item4446.text =energyModel.emergia_p_f14.toString()

                        binding.item5552.text=estateInfoModel.dos_anios_p_f15.toString()
                        binding.item5553.text =estateInfoModel.l_siembra_mante.toString()
                        binding.item5554.text=estateInfoModel.total_f15.toString()
                        binding.item5555.text =estateInfoModel.transformidad_f15.toString()
                        binding.item5556.text =energyModel.emergia_p_f15.toString()

                        binding.item6662.text=estateInfoModel.dos_anios_p_f16.toString()
                        binding.item6663.text =estateInfoModel.p_pest_fung.toString()
                        binding.item6664.text=estateInfoModel.total_f16.toString()
                        binding.item6665.text =estateInfoModel.transformidad_f16.toString()
                        binding.item6666.text =energyModel.emergia_p_f16.toString()
                        binding.item7771.text =estateInfoModel.total_p.toString()

                    }

                    Log.d("TAG", "Document data: ${estateDataModel}")
                } else {
                    Log.d("TAG", "Document does not exist")
                }
            }
            .addOnFailureListener { e ->
                Log.e("TAG", "Error getting document", e)
            }


    }

}