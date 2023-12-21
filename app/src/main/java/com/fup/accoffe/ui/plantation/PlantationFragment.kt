package com.fup.accoffe.ui.plantation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fup.accoffe.R
import com.fup.accoffe.adapters.EstateListAdapter
import com.fup.accoffe.adapters.PlantationListAdapter
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentPlantationBinding
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.fup.accoffe.models.EstateModel
import com.fup.accoffe.models.PlantationModel
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
 * Use the [PlantationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlantationFragment : Fragment() {
    private var _binding: FragmentPlantationBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance() //vara para arrtriba

    private lateinit var p_year: String
    private lateinit var p_vel_viento: String
    private lateinit var p_aspec_hidrico: String
    private lateinit var p_perdida_suelo: String
    private lateinit var p_urea: String
    private lateinit var p_albedo: String
    private lateinit var p_duracion_cult: String
    private lateinit var p_agua: String
    private lateinit var p_cont_materia_orga: String
    private lateinit var p_cal: String
    private lateinit var p_pest_fung: String
    private lateinit var p_promedio_inso: String
    private lateinit var p_evaporacion_poten: String
    private lateinit var p_promedio_altura: String
    private lateinit var p_ferti_nitro: String
    private lateinit var p_materia_orga: String
    private lateinit var l_siembra_mante: String
    private lateinit var p_capa_admos: String
    private lateinit var p_coefi_cult: String
    private lateinit var p_densidad: String
    private lateinit var p_ferti_fosfato: String
    private lateinit var p_semillas: String
    private lateinit var area_finca: String
    private lateinit var p_densidad_aire: String
    private lateinit var p_energia_libre_gibbs: String
    private lateinit var p_transpiracion: String
    private lateinit var p_ferti_potacio: String
    private lateinit var p_maq_man: String

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
        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        energyModel = EnergyModel()
        getState()
        obtainData()

        arguments?.let {

        }
    }

    private fun getState(){
        this.estateInfoModel.transformidad = transformidad;

        this.estateInfoModel.transformidad_f2 = transformidad_f2;

        this.estateInfoModel.transformidad_f3 = transformidad_f3;

        this.estateInfoModel.transformidad_f4 = transformidad_f4;

        this.estateInfoModel.transformidad_f5 = transformidad_f5;

        this.estateInfoModel.transformidad_f6 = transformidad_f6;

        this.estateInfoModel.transformidad_f7 = transformidad_f7;

        this.estateInfoModel.transformidad_f8 = transformidad_f8;

        this.estateInfoModel.transformidad_f9 = transformidad_f9;

        this.estateInfoModel.transformidad_f10 = transformidad_f10;

        this.estateInfoModel.transformidad_f11 = transformidad_f11;

        this.estateInfoModel.transformidad_f12 = transformidad_f12;

        this.estateInfoModel.transformidad_f13 = transformidad_f13;

        this.estateInfoModel.transformidad_f14 = transformidad_f14;

        this.estateInfoModel.transformidad_f15 = transformidad_f15;

        this.estateInfoModel.transformidad_f16 = transformidad_f16;
        var ins_m2 = 3600000
        var v_dolar2010 = 337.18
        var v_dolar2009 = 580.59


        Log.d("TAG", "getState: esta funcionado claro que si!!"+estateInfoModel.transformidad)

    }


    private fun obtainData(){
        val estateRef = db.collection("planting").document("YNRPfWwoI7QqToqE8Agt")

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


    }

    private fun getEstateId(): String? {
        val estateId = arguments?.getString("estateId")
        Log.d("plantation e.e", "Received estateId: $estateId")
        return  estateId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlantationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        savePlantation()
        return root
    }
    private fun savePlantation(){
    binding.botonSave.setOnClickListener {
        val plantingCollection = db.collection("planting")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                initViews()
                val plantation =PlantationModel("",
                    p_capa_admos.toDouble(),p_densidad_aire.toDouble(),p_promedio_altura.toDouble(),
                    p_evaporacion_poten.toDouble(),getEstateId(),p_cont_materia_orga.toDouble(), p_aspec_hidrico.toDouble(),
                    p_vel_viento.toDouble(),l_siembra_mante.toDouble(), p_maq_man.toDouble(),
                    p_ferti_potacio.toDouble(), p_coefi_cult.toDouble(), p_pest_fung.toDouble(),
                    p_materia_orga.toDouble(), p_agua.toDouble(), p_cal.toDouble(),
                    p_energia_libre_gibbs.toDouble(),p_promedio_inso.toDouble(),
                    p_albedo.toDouble(), p_semillas.toDouble(), p_ferti_nitro.toDouble(),
                    p_urea.toDouble(), p_ferti_fosfato.toDouble(),area_finca.toDouble(),
                    p_transpiracion.toDouble(), p_duracion_cult.toDouble(), p_year, p_densidad.toDouble(), p_perdida_suelo.toDouble())

                val documentReference = plantingCollection.add(plantation).await()
                println("Document created with ID: ${documentReference.id}")
            } catch (e: Exception) {
                // Manejar errores si es necesario
                e.printStackTrace()
            }
        }
    }
}

    private fun initViews() {
        p_year= binding.etPYear.text.toString()
        p_vel_viento= binding.etPVelViento.text.toString()
        p_aspec_hidrico= binding.etPAspecHidrico.text.toString()
        p_perdida_suelo= binding.etPPerdidaSuelo.text.toString()
        p_urea= binding.etPUrea.text.toString()
        p_albedo= binding.etPAlbedo.text.toString()
        p_duracion_cult= binding.etPDuracionCult.text.toString()
        p_agua= binding.etPAgua.text.toString()
        p_cont_materia_orga= binding.etPMateriaOrga.text.toString()
        p_cal= binding.etPCal.text.toString()
        p_pest_fung= binding.etPPestFung.text.toString()
        p_promedio_inso= binding.etPPromedioInso.text.toString()
        p_evaporacion_poten= binding.etPEvaporacionPoten.text.toString()
        p_promedio_altura= binding.etPPromedioAltura.text.toString()
        p_ferti_nitro= binding.etPFertiNitro.text.toString()
        p_materia_orga= binding.etPMateriaOrga.text.toString()
        l_siembra_mante= binding.etLSiembraMante.text.toString()
        p_capa_admos= binding.etPCapaAdmos.text.toString()
        p_coefi_cult= binding.etPCoefiCult.text.toString()
        p_densidad= binding.etPDensidad.text.toString()
        p_ferti_fosfato= binding.etPFertiFosfato.text.toString()
        p_semillas= binding.etPSemillas.text.toString()
        area_finca= binding.etAreaFinca.text.toString()
        p_densidad_aire= binding.etPDensidadAire.text.toString()
        p_energia_libre_gibbs= binding.etPEnergiaLibreGibbs.text.toString()
        p_transpiracion= binding.etPTranspiracion.text.toString()
        p_ferti_potacio= binding.etPFertiPotacio.text.toString()
        p_maq_man= binding.etPMaqMan.text.toString()
    }



}