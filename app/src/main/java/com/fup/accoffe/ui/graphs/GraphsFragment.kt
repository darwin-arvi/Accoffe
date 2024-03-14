package com.fup.accoffe.ui.graphs

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.fup.accoffe.databinding.FragmentGraphsBinding
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import com.google.firebase.firestore.FirebaseFirestore

class GraphsFragment : Fragment() {
    private var _binding: FragmentGraphsBinding? = null
    private val db = FirebaseFirestore.getInstance()
    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    //private lateinit var plantingId:String
    private lateinit var harvestId: String
    private lateinit var dryingId: String
    private lateinit var beatingId: String
    private var suma: Double = 0.0
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = GraphsFragment()
    }

    private lateinit var viewModel: GraphsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGraphsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        //plantingId=""
        harvestId = ""
        dryingId = ""
        beatingId = ""

        energyModel = EnergyModel()
        //getalldata()

        getStateData()

backGraphs()
        return root
    }
    private fun backGraphs() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }

    private fun getalldata() {
        val estateId = arguments?.getString("estateId")
        Log.d("estateidprueba", "Received estateId: $estateId")
        db.collection("planting").whereEqualTo("estateId", estateId).limit(1).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.documents[0]
                    var plantingId = document.id
                    Log.d("id planting", "getStateData: $plantingId")
                }
            }
    }

    private fun getStateData() {
        val transformidad = 1.0
        val transformidad_f2 = 2520.0
        var transformidad_f3 = 30600.0
        var transformidad_f4 = 17600.0
        var transformidad_f5 = 39800.0
        var transformidad_f6 = 74000.0
        var transformidad_f7 = 6620000000.0
        var transformidad_f8 = 9350000000.0
        var transformidad_f9 = 932000000.0
        var transformidad_f10 = 6620000000.0
        var transformidad_f11 = 1680000000.0
        var transformidad_f12 = 3870000000.0
        var transformidad_f13 = 58500.0
        var transformidad_f14 = 6700000000.0
        var transformidad_f15 = 22500000000000.0
        var transformidad_f16 = 14800000000.0
        var ins_m2 = 3600000.0

        var v_dolar2010 = 337.18
        var v_dolar2009 = 580.59



        //Log.d("getStateData", "Received estateId: $plantationId")
        val estateId = arguments?.getString("estateId")



        Log.d("estateidprueba", "Received estateId: $estateId")
        var plantingId: String = ""
        Log.d("pruebadolar","antesdeentrar$estateId")
        db.collection("estate").get().addOnSuccessListener {
            documents->
            Log.d("pruebadolar", "entro $estateId")
            if(!documents.isEmpty) {
               documents.forEach{document->
                   if (document.id==estateId){

                       val edolar = document.getDouble("edolar")
                       Log.d("pruebadolar", "entro $estateId ${document.id} $edolar")
                       db.collection("planting").whereEqualTo("estateId", estateId).limit(1).get()
                           .addOnSuccessListener { documents ->
                               if (!documents.isEmpty) {
                                   val document = documents.documents[0]
                                   plantingId = document.id
                                   Log.d("id planting", "getStateData: $plantingId")
                                   db.collection("planting").document(document.id).get()
                                       .addOnSuccessListener { documentSnapshot ->
                                           if (documentSnapshot.exists()) {

                                               val info = documentSnapshot.toObject(EstateDataModel::class.java)
                                               if (info != null) {
                                                   estateDataModel = info

                                                   estateInfoModel.transformidad = transformidad
                                                   estateInfoModel.transformidad_f2 = transformidad_f2
                                                   estateInfoModel.transformidad_f3 = transformidad_f3
                                                   estateInfoModel.transformidad_f4 = transformidad_f4
                                                   estateInfoModel.transformidad_f5 = transformidad_f5
                                                   estateInfoModel.transformidad_f6 = transformidad_f6
                                                   estateInfoModel.transformidad_f7 = transformidad_f7
                                                   estateInfoModel.transformidad_f8 = transformidad_f8
                                                   estateInfoModel.transformidad_f9 = transformidad_f9
                                                   estateInfoModel.transformidad_f10 = transformidad_f10
                                                   estateInfoModel.transformidad_f11 = transformidad_f11
                                                   estateInfoModel.transformidad_f12 = transformidad_f12
                                                   estateInfoModel.transformidad_f13 = transformidad_f13
                                                   estateInfoModel.transformidad_f14 = transformidad_f14
                                                   estateInfoModel.transformidad_f15 = transformidad_f15
                                                   estateInfoModel.transformidad_f16 = transformidad_f16


                                                   estateInfoModel.p_year = estateDataModel.p_year
                                                   estateInfoModel.p_albedo = estateDataModel.p_albedo
                                                   estateInfoModel.p_promedio_inso =
                                                       estateDataModel.p_promedio_inso
                                                   estateInfoModel.p_capa_admos = estateDataModel.p_capa_admos
                                                   estateInfoModel.p_densidad_aire =
                                                       estateDataModel.p_densidad_aire
                                                   estateInfoModel.p_vel_viento = estateDataModel.p_vel_viento
                                                   estateInfoModel.p_duracion_cult =
                                                       estateDataModel.p_duracion_cult
                                                   estateInfoModel.p_evaporacion_poten =
                                                       estateDataModel.p_evaporacion_poten
                                                   estateInfoModel.p_coefi_cult = estateDataModel.p_coefi_cult
                                                   estateInfoModel.p_energia_libre_gibbs =
                                                       estateDataModel.p_energia_libre_gibbs
                                                   estateInfoModel.p_aspec_hidrico =
                                                       estateDataModel.p_aspec_hidrico
                                                   estateInfoModel.p_agua = estateDataModel.p_agua
                                                   estateInfoModel.p_promedio_altura =
                                                       estateDataModel.p_promedio_altura
                                                   estateInfoModel.p_densidad = estateDataModel.p_densidad
                                                   estateInfoModel.p_transpiracion =
                                                       estateDataModel.p_transpiracion
                                                   estateInfoModel.p_perdida_suelo =
                                                       estateDataModel.p_perdida_suelo
                                                   estateInfoModel.p_cont_materia_orga =
                                                       estateDataModel.p_materia_orga
                                                   estateInfoModel.p_ferti_nitro = estateDataModel.p_ferti_nitro
                                                   estateInfoModel.p_ferti_fosfato =
                                                       estateDataModel.p_ferti_fosfato
                                                   estateInfoModel.p_ferti_potacio =
                                                       estateDataModel.p_ferti_potacio
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
                                                   var insilacion_p_uno =
                                                       estateDataModel.p_promedio_inso!! * ins_m2
                                                   var insolation_p_anio =
                                                       insilacion_p_uno!! * estateDataModel.p_duracion_cult!!
                                                   var anio_f1_p =
                                                       insolation_p_anio!! * (1 - estateDataModel.p_albedo!!) * estateDataModel.area_finca!!
                                                   this.estateInfoModel.anio_f1_p = anio_f1_p;

                                                   var dos_anios_p_f1 = (anio_f1_p / 30) * 2;

                                                   this.estateInfoModel.dos_anios_p_f1 = dos_anios_p_f1;

                                                   var total_f1 = dos_anios_p_f1 + anio_f1_p;

                                                   this.estateInfoModel.total_f1 = total_f1;

                                                   var emergia_p_f1 = total_f1 * transformidad

                                                   //PLANTACION FILA 2
                                                   var anio_f2_p =
                                                       estateDataModel.p_capa_admos!! * estateDataModel.area_finca!! * estateDataModel.p_densidad_aire!! * estateDataModel.p_vel_viento!! * (estateDataModel.p_duracion_cult!! / 365);
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
                                                   var evap_p_f3 =
                                                       (evap_p * estateDataModel.p_duracion_cult!!) / 1000;
                                                   var cropEt_p_f3 = evap_p_f3 * estateDataModel.p_coefi_cult!!
                                                   //año iniciales
                                                   var anio_f3_p =
                                                       estateDataModel.area_finca!! * cropEt_p_f3 * estateDataModel.p_energia_libre_gibbs!! * 1000;
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
                                                   var anio_f4_p =
                                                       estateDataModel.area_finca!! * estateDataModel.p_aspec_hidrico!! * estateDataModel.p_agua!! * estateDataModel.p_promedio_altura!! * estateDataModel.p_densidad!! * gravedad_f4;

                                                   var dos_anios_p_f4 = (anio_f4_p / 30) * 2;
                                                   var total_f4 = anio_f4_p + dos_anios_p_f4;
                                                   var emergia_p_f4 = total_f4 * transformidad_f4

                                                   //PLANTACION F5
                                                   //año analizado f5
                                                   val anio_f5_p =
                                                       estateDataModel.area_finca!! * estateDataModel.p_transpiracion!! * estateDataModel.p_densidad!! * estateDataModel.p_energia_libre_gibbs!!
                                                   var dos_anios_p_f5 = (anio_f5_p / 30) * 2;
                                                   var total_f5 = anio_f5_p + dos_anios_p_f5;
                                                   var emergia_p_f5 = total_f5 * transformidad_f5;

                                                   //PLANTACION F6
                                                   val kcal = 5400;
                                                   val jkcal = 4186;
                                                   //año analizado f6
                                                   val anio_f6_p =
                                                       estateDataModel.area_finca!! * estateDataModel.p_perdida_suelo!! * estateDataModel.p_cont_materia_orga!! * kcal * jkcal;
                                                   //dos años analizados
                                                   var dos_anios_p_f6 = (anio_f6_p / 30) * 2;
                                                   var total_f6 = anio_f6_p + dos_anios_p_f6;
                                                   var emergia_p_f6 = total_f6 * transformidad_f6;

                                                   //PLANTACION F7
                                                   var dos_anios_p_f7 = (estateDataModel.p_ferti_nitro!! / 30) * 2;
                                                   var total_f7 = dos_anios_p_f7 + estateDataModel.p_ferti_nitro!!;
                                                   var emergia_p_f7 = total_f7 * transformidad_f7;

                                                   //PLANTACION F8
                                                   var dos_anios_p_f8 =
                                                       (estateDataModel.p_ferti_fosfato!! / 30) * 2;
                                                   var total_f8 =
                                                       estateDataModel.p_ferti_fosfato!! + dos_anios_p_f8;
                                                   var emergia_p_f8 = total_f8 * transformidad_f8;

                                                   //PLANTACION F9
                                                   var dos_anios_p_f9 =
                                                       (estateDataModel.p_ferti_potacio!! / 30) * 2;
                                                   var total_f9 =
                                                       dos_anios_p_f9 + estateDataModel.p_ferti_potacio!!;
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
                                                   this.estateInfoModel.l_siembra_mante =
                                                       estateDataModel.l_siembra_mante;

                                                   //   this.estateInfo.l_siembra_mante = l_siembra_mante;
                                                   var dos_anios_p_f15 = (v_dolar2009 + v_dolar2010) / 30;
                                                   var total_f15 =
                                                       dos_anios_p_f15 + estateDataModel.l_siembra_mante!!;
                                                   var emergia_p_f15 = total_f15 * transformidad_f15;

                                                   //PLANTACION F16 l_siembra_mante
                                                   var dos_anios_p_f16 = (estateDataModel.p_pest_fung!! / 30) * 2;
                                                   var total_f16 = estateDataModel.p_pest_fung!! + dos_anios_p_f16;
                                                   var emergia_p_f16 = total_f16 * transformidad_f16;

                                                   //TOTAL PLANTACION
                                                   var total_P =
                                                       emergia_p_f1 + emergia_p_f2 + emergia_p_f3 + emergia_p_f4 + emergia_p_f5 + emergia_p_f6 + emergia_p_f7 + emergia_p_f8 + emergia_p_f9 + emergia_p_f10 + emergia_p_f11 + emergia_p_f12 + emergia_p_f13 + emergia_p_f14 + emergia_p_f15 + emergia_p_f16;

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

                                                   var transformidad_f17 = 6700000000.0
                                                   var transformidad_f18 = 111111.0
                                                   var transformidad_f19 = 22500000000000.0
                                                   var transformidad_c20 = 22500000000000.0
                                                   var anio_analizado_c1 =
                                                       1.0 // le asigne un valor para que no genere error

                                                   Log.d("infoplanting", "getStateData: " + emergia_p_f1)
                                                   suma += emergia_p_f1

                                                   Log.d("sumaprueba", "suma : " + suma)
                                                   //harvest

                                                   db.collection("harvest").whereEqualTo("estateId", estateId)
                                                       .limit(1).get().addOnSuccessListener { documents ->
                                                           if (!documents.isEmpty) {
                                                               val document = documents.documents[0]
                                                               harvestId = document.id
                                                               Log.d("id harvest", "getStateData: $harvestId")
                                                               db.collection("harvest").document(harvestId).get()
                                                                   .addOnSuccessListener { documentSnapshot ->
                                                                       if (documentSnapshot.exists()) {

                                                                           val info = documentSnapshot.toObject(
                                                                               EstateDataModel::class.java
                                                                           )
                                                                           if (info != null) {
                                                                               estateDataModel = info


                                                                               //asiganmos constantes al objeto

                                                                               this.estateInfoModel.transformidad_f17 =
                                                                                   transformidad_f17
                                                                               this.estateInfoModel.transformidad_f18 =
                                                                                   transformidad_f18
                                                                               this.estateInfoModel.transformidad_f19 =
                                                                                   transformidad_f19
                                                                               this.estateInfoModel.transformidad_c20 =
                                                                                   transformidad_c20
                                                                               this.estateInfoModel.anio_analizado_c1 =
                                                                                   anio_analizado_c1

                                                                               //procedemos con las operaciones
                                                                               ///operaciones
                                                                               //COSECHA F17
                                                                               val emergia_p_f17 =
                                                                                   estateDataModel.h_maq_man!! * transformidad_f17
                                                                               //COSECHA F18
                                                                               var anio_analizado_c1 =
                                                                                   estateDataModel.h_combustible!! * 39500000
                                                                               val emergia_p_f18 =
                                                                                   anio_analizado_c1 * transformidad_f18
                                                                               //COSECHA F19
                                                                               val emergia_p_f19 =
                                                                                   estateDataModel.h_transport!! * transformidad_f19
                                                                               //COSECHA F20
                                                                               val emergia_p_f20 =
                                                                                   estateDataModel.l_Pjornal_recole!! * transformidad_c20
                                                                               //TOTAL COSECHA
                                                                               var total_c =
                                                                                   emergia_p_f17 + emergia_p_f18 + emergia_p_f19 + emergia_p_f20

                                                                               this.estateInfoModel.total_c =
                                                                                   total_c

                                                                               this.energyModel.emergia_p_f17 =
                                                                                   emergia_p_f17
                                                                               this.energyModel.emergia_p_f18 =
                                                                                   emergia_p_f18
                                                                               this.energyModel.emergia_p_f19 =
                                                                                   emergia_p_f19
                                                                               this.energyModel.emergia_p_f20 =
                                                                                   emergia_p_f20




                                                                               Log.d(
                                                                                   "harvestable",
                                                                                   "datos solicitados: ${estateInfoModel.transformidad_f17} ${estateDataModel.h_maq_man} ${energyModel.emergia_p_f17}"
                                                                               )
                                                                               //drying


                                                                               var transformidad_s3 = 145000.0
                                                                               var transformidad_s4 = 86000.0
                                                                               var transformidad_s5 = 2420000000.0
                                                                               var transformidad_s6 =
                                                                                   26500000000000.0
                                                                               var transformidad_s7 =
                                                                                   22500000000000.0
                                                                               var transformidad_s8 = 111111.0
                                                                               var transformidad_s9 =
                                                                                   26500000000000.0
                                                                               var transformidad_s30 =
                                                                                   26500000000000.0
                                                                               var v_dolar2014 = 2000.0


                                                                               try {

                                                                                   db.collection("drying")
                                                                                       .whereEqualTo(
                                                                                           "estateId", estateId
                                                                                       ).limit(1).get()
                                                                                       .addOnSuccessListener { documents ->
                                                                                           if (!documents.isEmpty) {
                                                                                               val document =
                                                                                                   documents.documents[0]
                                                                                               plantingId =
                                                                                                   document.id


                                                                                               db.collection("drying")
                                                                                                   .document(
                                                                                                       document.id
                                                                                                   ).get()
                                                                                                   .addOnSuccessListener { documentSnapshot ->
                                                                                                       if (documentSnapshot.exists()) {

                                                                                                           val info =
                                                                                                               documentSnapshot.toObject(
                                                                                                                   EstateDataModel::class.java
                                                                                                               )
                                                                                                           if (info != null) {
                                                                                                               estateDataModel =
                                                                                                                   info

                                                                                                               ///operaciones

                                                                                                               //FILA21
                                                                                                               val insolacion_s =
                                                                                                                   estateDataModel.p_insolation!! * 3600000
                                                                                                               val insolacion_diez =
                                                                                                                   insolacion_s * 10
                                                                                                               val flujo_anual_s1 =
                                                                                                                   insolacion_diez * (1 - estateDataModel.p_albedo!!) * estateDataModel.area_finca!!
                                                                                                               var emergia_s1 =
                                                                                                                   flujo_anual_s1 * transformidad

                                                                                                               //FILA22
                                                                                                               val flujo_anual_s2 =
                                                                                                                   estateDataModel.area_finca!! * estateDataModel.p_admosfra!! * estateDataModel.p_densidadA!! * estateDataModel.p_viento!!
                                                                                                               var emergia_s2 =
                                                                                                                   flujo_anual_s2 * transformidad_f2

                                                                                                               //FILA23
                                                                                                               val area_finca_1 =
                                                                                                                   10//////estateData.area_finca
                                                                                                               val evapo_energ =
                                                                                                                   area_finca_1 * estateDataModel.p_transpiracion!! * estateDataModel.p_densidad2!! * estateDataModel.p_enegia_lib!!
                                                                                                               val flujo_anual_s3 =
                                                                                                                   (evapo_energ / 365) * 10
                                                                                                               var emergia_s3 =
                                                                                                                   flujo_anual_s3 * transformidad_s3

                                                                                                               //FILA24
                                                                                                               val prod_perg =
                                                                                                                   estateDataModel.d_produccion!! / 5
                                                                                                               val prod_anio =
                                                                                                                   (prod_perg * 2) / 1000
                                                                                                               //const water_cons = prod_anio / 1000
                                                                                                               val flujo_anual_s4 =
                                                                                                                   prod_anio * estateDataModel.d_k!! * estateDataModel.p_enegia_lib!!
                                                                                                               var emergia_s4 =
                                                                                                                   flujo_anual_s4 * transformidad_s4

                                                                                                               //FILA25
                                                                                                               val flujo_anual_s5 =
                                                                                                                   estateDataModel.d_patio_secado!! * 1000
                                                                                                               var emergia_s5 =
                                                                                                                   flujo_anual_s5 * transformidad_s5

                                                                                                               //FILA26
                                                                                                               val flujo_anual_s6 =
                                                                                                                   ((estateDataModel.d_val_maq!! / v_dolar2014) / 30) / 118
                                                                                                               var emergia_s6 =
                                                                                                                   flujo_anual_s6 * transformidad_s6
                                                                                                               //FILA27
                                                                                                               //const gastos_benef_lh = 16683.73;
                                                                                                               val flujo_anual_s7 =
                                                                                                                   (estateDataModel.d_gf_Benef!!) / 118
                                                                                                               var emergia_s7 =
                                                                                                                   flujo_anual_s7 * transformidad_s7

                                                                                                               //FILA28
                                                                                                               val flujo_anual_s8 =
                                                                                                                   estateDataModel.d_combustible!! * 39500000
                                                                                                               var emergia_s8 =
                                                                                                                   flujo_anual_s8 * transformidad_s8
                                                                                                               //FILA29
                                                                                                               val flujo_anual_s9 =
                                                                                                                   ((estateDataModel.d_val_infra!! / v_dolar2014) / 50) / 118
                                                                                                               var emergia_s9 =
                                                                                                                   flujo_anual_s9 * transformidad_s9

                                                                                                               //FILA30
                                                                                                               val flujo_anual_s30 =
                                                                                                                   ((estateDataModel.d_promedio_electrico!! * 12) / estateDataModel.l_Pdolar!!) / 118
                                                                                                               var emergia_s30 =
                                                                                                                   flujo_anual_s30 * transformidad_s30

                                                                                                               //TOTAL
                                                                                                               var total_s =
                                                                                                                   emergia_s1 + emergia_s2 + emergia_s3 + emergia_s4 + emergia_s5 + emergia_s6 + emergia_s7 + emergia_s8 + emergia_s9 + emergia_s30

                                                                                                               this.estateInfoModel.flujo_anual_s1 =
                                                                                                                   flujo_anual_s1
                                                                                                               this.estateInfoModel.flujo_anual_s2 =
                                                                                                                   flujo_anual_s2
                                                                                                               this.estateInfoModel.flujo_anual_s3 =
                                                                                                                   flujo_anual_s3
                                                                                                               this.estateInfoModel.flujo_anual_s4 =
                                                                                                                   flujo_anual_s4
                                                                                                               this.estateInfoModel.flujo_anual_s5 =
                                                                                                                   flujo_anual_s5
                                                                                                               this.estateInfoModel.flujo_anual_s6 =
                                                                                                                   flujo_anual_s6
                                                                                                               this.estateInfoModel.flujo_anual_s7 =
                                                                                                                   flujo_anual_s7
                                                                                                               this.estateInfoModel.flujo_anual_s8 =
                                                                                                                   flujo_anual_s8
                                                                                                               this.estateInfoModel.flujo_anual_s9 =
                                                                                                                   flujo_anual_s9
                                                                                                               this.estateInfoModel.flujo_anual_s30 =
                                                                                                                   flujo_anual_s30
                                                                                                               this.estateInfoModel.transformidad =
                                                                                                                   transformidad
                                                                                                               this.estateInfoModel.transformidad_f2 =
                                                                                                                   transformidad_f2
                                                                                                               this.estateInfoModel.transformidad_s3 =
                                                                                                                   transformidad_s3
                                                                                                               this.estateInfoModel.transformidad_s4 =
                                                                                                                   transformidad_s4
                                                                                                               this.estateInfoModel.transformidad_s5 =
                                                                                                                   transformidad_s5
                                                                                                               this.estateInfoModel.transformidad_s6 =
                                                                                                                   transformidad_s6
                                                                                                               this.estateInfoModel.transformidad_s7 =
                                                                                                                   transformidad_s7
                                                                                                               this.estateInfoModel.transformidad_s8 =
                                                                                                                   transformidad_s8
                                                                                                               this.estateInfoModel.transformidad_s9 =
                                                                                                                   transformidad_s9
                                                                                                               this.estateInfoModel.transformidad_s30 =
                                                                                                                   transformidad_s30
                                                                                                               this.energyModel.emergia_s1 =
                                                                                                                   emergia_s1
                                                                                                               this.energyModel.emergia_s2 =
                                                                                                                   emergia_s2
                                                                                                               this.energyModel.emergia_s3 =
                                                                                                                   emergia_s3
                                                                                                               this.energyModel.emergia_s4 =
                                                                                                                   emergia_s4
                                                                                                               this.energyModel.emergia_s5 =
                                                                                                                   emergia_s5
                                                                                                               this.energyModel.emergia_s6 =
                                                                                                                   emergia_s6
                                                                                                               this.energyModel.emergia_s7 =
                                                                                                                   emergia_s7
                                                                                                               this.energyModel.emergia_s8 =
                                                                                                                   emergia_s8
                                                                                                               this.energyModel.emergia_s9 =
                                                                                                                   emergia_s9
                                                                                                               this.energyModel.emergia_s30 =
                                                                                                                   emergia_s30
                                                                                                               this.estateInfoModel.total_s =
                                                                                                                   total_s


                                                                                                               //beaten


                                                                                                               val transformidad_t1 =
                                                                                                                   26500000000000.0
                                                                                                               val transformidad_t2 =
                                                                                                                   22500000000000.0
                                                                                                               val transformidad_t5 =
                                                                                                                   23100000000.0

                                                                                                               db.collection(
                                                                                                                   "beaten"
                                                                                                               )
                                                                                                                   .whereEqualTo(
                                                                                                                       "estateId",
                                                                                                                       estateId
                                                                                                                   )
                                                                                                                   .limit(
                                                                                                                       1
                                                                                                                   )
                                                                                                                   .get()
                                                                                                                   .addOnSuccessListener { documents ->
                                                                                                                       if (!documents.isEmpty) {
                                                                                                                           val document =
                                                                                                                               documents.documents[0]
                                                                                                                           plantingId =
                                                                                                                               document.id
                                                                                                                           db.collection(
                                                                                                                               "beaten"
                                                                                                                           )
                                                                                                                               .document(
                                                                                                                                   document.id
                                                                                                                               )
                                                                                                                               .get()
                                                                                                                               .addOnSuccessListener { documentSnapshot ->
                                                                                                                                   if (documentSnapshot.exists()) {

                                                                                                                                       val info =
                                                                                                                                           documentSnapshot.toObject(
                                                                                                                                               EstateDataModel::class.java
                                                                                                                                           )
                                                                                                                                       if (info != null) {
                                                                                                                                           estateDataModel =
                                                                                                                                               info

                                                                                                                                           ///operaciones

                                                                                                                                           var flujo1 =
                                                                                                                                               ((estateDataModel.b_val_maq!! / v_dolar2014) / 30) / 118
                                                                                                                                           val t1 =
                                                                                                                                               (flujo1 * transformidad_t1)

                                                                                                                                           this.estateInfoModel.flujo1 =
                                                                                                                                               flujo1;
                                                                                                                                           this.estateInfoModel.t1 =
                                                                                                                                               t1;

                                                                                                                                           //const flujo2 = 14244 / 118;
                                                                                                                                           val flujo2 =
                                                                                                                                               (estateDataModel.b_gf_Benef!!) / 118;
                                                                                                                                           var t2 =
                                                                                                                                               (flujo2 * transformidad_t2);

                                                                                                                                           this.estateInfoModel.flujo2 =
                                                                                                                                               flujo2;
                                                                                                                                           this.estateInfoModel.t2 =
                                                                                                                                               t2;

                                                                                                                                           val flujo3 =
                                                                                                                                               ((estateDataModel.b_val_infra!! / v_dolar2014) / 50) / 118;
                                                                                                                                           var t3 =
                                                                                                                                               (flujo3 * transformidad_t1);

                                                                                                                                           this.estateInfoModel.flujo3 =
                                                                                                                                               flujo3;
                                                                                                                                           this.estateInfoModel.t3 =
                                                                                                                                               t3;

                                                                                                                                           val flujo4 =
                                                                                                                                               ((estateDataModel.b_promed_electrico!! * 12) / estateDataModel.b_dolar!!) / 118;
                                                                                                                                           var t4 =
                                                                                                                                               (flujo4 * transformidad_t1);

                                                                                                                                           this.estateInfoModel.flujo4 =
                                                                                                                                               flujo4;
                                                                                                                                           this.estateInfoModel.t4 =
                                                                                                                                               t4;

                                                                                                                                           var t5 =
                                                                                                                                               (estateDataModel.b_num_costales!! * transformidad_t5);
                                                                                                                                           this.estateInfoModel.t5 =
                                                                                                                                               t5;

                                                                                                                                           this.estateInfoModel.total =
                                                                                                                                               t1 + t2 + t3 + t4 + t5

                                                                                                                                           this.estateInfoModel.b_num_costales =
                                                                                                                                               estateDataModel.b_num_costales
                                                                                                                                           this.estateInfoModel.b_promed_electrico =
                                                                                                                                               estateDataModel.b_promed_electrico
                                                                                                                                           this.estateInfoModel.b_gf_Benef =
                                                                                                                                               estateDataModel.b_gf_Benef
                                                                                                                                           this.estateInfoModel.b_val_infra =
                                                                                                                                               estateDataModel.b_val_infra
                                                                                                                                           this.estateInfoModel.b_year =
                                                                                                                                               estateDataModel.b_year
                                                                                                                                           this.estateInfoModel.b_val_maq =
                                                                                                                                               estateDataModel.b_val_maq
                                                                                                                                           this.estateInfoModel.transformidad_t1 =
                                                                                                                                               transformidad_t1
                                                                                                                                           this.estateInfoModel.transformidad_t2 =
                                                                                                                                               transformidad_t2
                                                                                                                                           this.estateInfoModel.transformidad_t5 =
                                                                                                                                               transformidad_t5
                                                                                                                                           val dolar =
                                                                                                                                               estateDataModel.b_dolar


                                                                                                                                           //SUMA PLANTING
                                                                                                                                           val plantingsum =
                                                                                                                                               emergia_p_f1 + emergia_p_f2 + emergia_p_f3 + emergia_p_f4 + emergia_p_f5 + emergia_s1 + emergia_s2 + emergia_s3 + emergia_s4
                                                                                                                                           val reno2 =
                                                                                                                                               (emergia_p_f12 * 0.8) + (emergia_p_f15 * 0.1) + (emergia_p_f20 * 0.1) + (emergia_s7 * 0.1) + (t2 * 0.1)
                                                                                                                                           val noreno2 =
                                                                                                                                               (emergia_p_f7 + emergia_p_f8 + emergia_p_f9 + emergia_p_f10 + emergia_p_f11 + emergia_p_f12 * 0.2 + emergia_p_f13 + emergia_p_f14 + (emergia_p_f15 * 0.9) + emergia_p_f16) + emergia_p_f17 + emergia_p_f18 + emergia_p_f19 + (emergia_p_f20 * 0.9) + emergia_s5 + emergia_s6 + (emergia_s7 * 0.9) + emergia_s8 + emergia_s9 + emergia_s30 + t1 + (t2 * 0.9) + t3 + t4 + t5
                                                                                                                                           val totalf =
                                                                                                                                               plantingsum + emergia_p_f6 + reno2 + noreno2

                                                                                                                                           val siwret =
                                                                                                                                               emergia_p_f1 + emergia_p_f2 + emergia_p_f3 + emergia_p_f4 + emergia_p_f5;
                                                                                                                                           val fertilizers =
                                                                                                                                               emergia_p_f7 + emergia_p_f8 + emergia_p_f9 + emergia_p_f10 + emergia_p_f11
                                                                                                                                           val organicFertilizer =
                                                                                                                                               emergia_p_f12
                                                                                                                                           val humanlabor =
                                                                                                                                               emergia_p_f15
                                                                                                                                           val machinaryandequipment =
                                                                                                                                               emergia_p_f14
                                                                                                                                           //harvest
                                                                                                                                           val machinaryandEquipmentH =
                                                                                                                                               emergia_p_f17
                                                                                                                                           val fuelandlubricants =
                                                                                                                                               emergia_p_f18
                                                                                                                                           val HTvehicle =
                                                                                                                                               emergia_p_f19
                                                                                                                                           val humanlaborH =
                                                                                                                                               emergia_p_f20
                                                                                                                                           //drying
                                                                                                                                           val siweD =
                                                                                                                                               emergia_s1 + emergia_s2 + emergia_s3
                                                                                                                                           val waterD =
                                                                                                                                               emergia_s4
                                                                                                                                           val machinaryandEquipmentD =
                                                                                                                                               emergia_s6
                                                                                                                                           val humanlaborD =
                                                                                                                                               emergia_s7
                                                                                                                                           val buildingsD =
                                                                                                                                               emergia_s9 + emergia_s5
                                                                                                                                           val electricityandfuelD =
                                                                                                                                               emergia_s30 + emergia_s8
                                                                                                                                           //preprocessing
                                                                                                                                           val machinaryandequipmentP =
                                                                                                                                               t1
                                                                                                                                           val humanlaborP =
                                                                                                                                               t2
                                                                                                                                           val buildingsP =
                                                                                                                                               t3
                                                                                                                                           val electricityP =
                                                                                                                                               t4


                                                                                                                                           if (estateId != null) {
                                                                                                                                               db.collection(
                                                                                                                                                   "estate"
                                                                                                                                               )
                                                                                                                                                   .document(
                                                                                                                                                       estateId
                                                                                                                                                   )
                                                                                                                                                   .get()
                                                                                                                                                   .addOnSuccessListener { document ->


                                                                                                                                                       val factor_f =
                                                                                                                                                           document.getDouble(
                                                                                                                                                               "econvertionalmendra"
                                                                                                                                                           )
                                                                                                                                                       val equiv1k =
                                                                                                                                                           24500000
                                                                                                                                                       val s_prodCereza =
                                                                                                                                                           0.18
                                                                                                                                                       val prodAlmendra =
                                                                                                                                                           s_prodCereza * factor_f!!

                                                                                                                                                       val flujoAnual =
                                                                                                                                                           prodAlmendra * equiv1k;

                                                                                                                                                       val transf2018 =
                                                                                                                                                           totalf / flujoAnual;

                                                                                                                                                       try{requireActivity().runOnUiThread {
                                                                                                                                                           binding.resul5.text =
                                                                                                                                                               String.format(
                                                                                                                                                                   "%.2e",
                                                                                                                                                                   transf2018
                                                                                                                                                               )
                                                                                                                                                       }}catch(e:Exception){
                                                                                                                                                           Log.d(
                                                                                                                                                               "errorrrr",
                                                                                                                                                               "getStateData: $e"
                                                                                                                                                           )
                                                                                                                                                       }

                                                                                                                                                   }
                                                                                                                                           }

                                                                                                                                           val eyr =
                                                                                                                                               (totalf / (reno2 + noreno2));
                                                                                                                                           val elr =
                                                                                                                                               ((emergia_p_f6 + noreno2) / (plantingsum + reno2))
                                                                                                                                           val eir =
                                                                                                                                               ((reno2 + noreno2) / (emergia_p_f6 + plantingsum))
                                                                                                                                           val esi =
                                                                                                                                               (eyr / elr)

                                                                                                                                           try {
                                                                                                                                               requireActivity().runOnUiThread {
                                                                                                                                                   binding.renovables1.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           plantingsum
                                                                                                                                                       )
                                                                                                                                                   binding.Rnorenovables.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           emergia_p_f6
                                                                                                                                                       )
                                                                                                                                                   binding.reno2.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           reno2
                                                                                                                                                       )
                                                                                                                                                   binding.noreno2.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           noreno2
                                                                                                                                                       )
                                                                                                                                                   binding.totalf.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           totalf
                                                                                                                                                       )
                                                                                                                                                   //transformidad
                                                                                                                                                   binding.resul1.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           eyr
                                                                                                                                                       )
                                                                                                                                                   binding.resul2.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           elr
                                                                                                                                                       )
                                                                                                                                                   binding.resul3.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           eir
                                                                                                                                                       )
                                                                                                                                                   binding.resul4.text =
                                                                                                                                                       String.format(
                                                                                                                                                           "%.2e",
                                                                                                                                                           esi
                                                                                                                                                       )

                                                                                                                                                   //graficas

                                                                                                                                                   val chart: BarChart =
                                                                                                                                                       binding.chart1
                                                                                                                                                   val entries =
                                                                                                                                                       ArrayList<BarEntry>()

                                                                                                                                                   entries.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           0f,
                                                                                                                                                           plantingsum.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           1f,
                                                                                                                                                           emergia_p_f6.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           2f,
                                                                                                                                                           reno2.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           3f,
                                                                                                                                                           noreno2.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )

                                                                                                                                                   val dataSet =
                                                                                                                                                       BarDataSet(
                                                                                                                                                           entries,
                                                                                                                                                           ""
                                                                                                                                                       )
                                                                                                                                                   val dataSets =
                                                                                                                                                       ArrayList<BarDataSet>()
                                                                                                                                                   dataSets.add(
                                                                                                                                                       dataSet
                                                                                                                                                   )
                                                                                                                                                   dataSet.colors =
                                                                                                                                                       listOf(
                                                                                                                                                           Color.BLUE,
                                                                                                                                                           Color.BLACK,
                                                                                                                                                           Color.MAGENTA,
                                                                                                                                                           Color.YELLOW
                                                                                                                                                       )
                                                                                                                                                   dataSet.setDrawValues(
                                                                                                                                                       false
                                                                                                                                                   )
                                                                                                                                                   val data =
                                                                                                                                                       BarData(
                                                                                                                                                           dataSets as List<IBarDataSet>?
                                                                                                                                                       )

                                                                                                                                                   chart.data =
                                                                                                                                                       data

                                                                                                                                                   chart.axisRight.isEnabled =
                                                                                                                                                       false
                                                                                                                                                   chart.axisLeft.isEnabled =
                                                                                                                                                       false
                                                                                                                                                   chart.xAxis.isEnabled =
                                                                                                                                                       false

                                                                                                                                                   chart.invalidate()


                                                                                                                                                   val pieChart: PieChart =
                                                                                                                                                       binding.chart2
                                                                                                                                                   val entries2 =
                                                                                                                                                       ArrayList<PieEntry>()
                                                                                                                                                   entries2.add(
                                                                                                                                                       PieEntry(
                                                                                                                                                           plantingsum.toFloat(),
                                                                                                                                                           "R-Renova.."
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries2.add(
                                                                                                                                                       PieEntry(
                                                                                                                                                           emergia_p_f6.toFloat(),
                                                                                                                                                           "R-No Renova.."
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries2.add(
                                                                                                                                                       PieEntry(
                                                                                                                                                           reno2.toFloat(),
                                                                                                                                                           "N-Renova.."
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries2.add(
                                                                                                                                                       PieEntry(
                                                                                                                                                           noreno2.toFloat(),
                                                                                                                                                           "N-No Renova.."
                                                                                                                                                       )
                                                                                                                                                   )

                                                                                                                                                   val dataSet2 =
                                                                                                                                                       PieDataSet(
                                                                                                                                                           entries2,
                                                                                                                                                           "  "
                                                                                                                                                       )
                                                                                                                                                   dataSet2.colors =
                                                                                                                                                       listOf(
                                                                                                                                                           Color.BLUE,
                                                                                                                                                           Color.BLACK,
                                                                                                                                                           Color.MAGENTA,
                                                                                                                                                           Color.YELLOW
                                                                                                                                                       )

                                                                                                                                                   val formatter =
                                                                                                                                                       PercentFormatter(
                                                                                                                                                           pieChart
                                                                                                                                                       )
                                                                                                                                                   dataSet2.valueFormatter =
                                                                                                                                                       formatter

                                                                                                                                                   val data2 =
                                                                                                                                                       PieData(
                                                                                                                                                           dataSet2
                                                                                                                                                       )
                                                                                                                                                   data2.setValueFormatter(
                                                                                                                                                       formatter
                                                                                                                                                   )

                                                                                                                                                   pieChart.data =
                                                                                                                                                       data2
                                                                                                                                                   val legend =
                                                                                                                                                       pieChart.legend

                                                                                                                                                   dataSet2.valueTextColor =
                                                                                                                                                       Color.BLACK
                                                                                                                                                   dataSet2.valueTextSize =
                                                                                                                                                       12f
                                                                                                                                                   legend.textColor =
                                                                                                                                                       Color.BLACK
                                                                                                                                                   legend.textSize =
                                                                                                                                                       12f

                                                                                                                                                   pieChart.invalidate()


                                                                                                                                                   val chart3: BarChart =
                                                                                                                                                       binding.chart3
                                                                                                                                                   val entries3 =

                                                                                                                                                       ArrayList<BarEntry>()

                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           1f,
                                                                                                                                                           siwret.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           2f,
                                                                                                                                                           fertilizers.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           3f,
                                                                                                                                                           organicFertilizer.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           4f,
                                                                                                                                                           humanlabor.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           5f,
                                                                                                                                                           machinaryandequipment.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           6f,
                                                                                                                                                           machinaryandEquipmentH.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           7f,
                                                                                                                                                           fuelandlubricants.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           8f,
                                                                                                                                                           HTvehicle.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           9f,
                                                                                                                                                           humanlaborH.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           10f,
                                                                                                                                                           siweD.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           11f,
                                                                                                                                                           waterD.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           12f,
                                                                                                                                                           machinaryandEquipmentD.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           13f,
                                                                                                                                                           humanlaborD.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           14f,
                                                                                                                                                           buildingsD.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           15f,
                                                                                                                                                           electricityandfuelD.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           16f,
                                                                                                                                                           machinaryandequipmentP.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           17f,
                                                                                                                                                           humanlaborP.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           18f,
                                                                                                                                                           buildingsP.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )
                                                                                                                                                   entries3.add(
                                                                                                                                                       BarEntry(
                                                                                                                                                           19f,
                                                                                                                                                           electricityP.toFloat()
                                                                                                                                                       )
                                                                                                                                                   )


                                                                                                                                                   val dataSet3 =
                                                                                                                                                       BarDataSet(
                                                                                                                                                           entries3,
                                                                                                                                                           ""
                                                                                                                                                       )
                                                                                                                                                   dataSet3.colors =
                                                                                                                                                       listOf(
                                                                                                                                                           Color.GREEN,
                                                                                                                                                           Color.GREEN,
                                                                                                                                                           Color.GREEN,
                                                                                                                                                           Color.GREEN,
                                                                                                                                                           Color.GREEN,
                                                                                                                                                           Color.RED,
                                                                                                                                                           Color.RED,
                                                                                                                                                           Color.RED,
                                                                                                                                                           Color.RED,
                                                                                                                                                           Color.YELLOW,
                                                                                                                                                           Color.YELLOW,
                                                                                                                                                           Color.YELLOW,
                                                                                                                                                           Color.YELLOW,
                                                                                                                                                           Color.YELLOW,
                                                                                                                                                           Color.YELLOW,
                                                                                                                                                           Color.BLUE,
                                                                                                                                                           Color.BLUE,
                                                                                                                                                           Color.BLUE,
                                                                                                                                                           Color.BLUE
                                                                                                                                                       )
                                                                                                                                                   val dataSets3 =
                                                                                                                                                       ArrayList<BarDataSet>()
                                                                                                                                                   dataSets3.add(
                                                                                                                                                       dataSet3
                                                                                                                                                   )

                                                                                                                                                   val data3 =
                                                                                                                                                       BarData(
                                                                                                                                                           dataSets3 as List<IBarDataSet>?
                                                                                                                                                       )


                                                                                                                                                   chart3.data =
                                                                                                                                                       data3
                                                                                                                                                   chart3.axisRight.isEnabled =
                                                                                                                                                       false
                                                                                                                                                   chart3.axisLeft.isEnabled =
                                                                                                                                                       false
                                                                                                                                                   chart3.xAxis.isEnabled =
                                                                                                                                                       false
                                                                                                                                                   chart3.invalidate()
                                                                                                                                               }
                                                                                                                                           }catch (e:Exception){
                                                                                                                                               Log.d(
                                                                                                                                                   "errorrrr",
                                                                                                                                                   "getStateData: $e"
                                                                                                                                               )
                                                                                                                                           }

                                                                                                                                           Log.d(
                                                                                                                                               "DATOSVALIDOS",
                                                                                                                                               "R renovables: ${
                                                                                                                                                   String.format(
                                                                                                                                                       "%.2e",
                                                                                                                                                       noreno2
                                                                                                                                                   )
                                                                                                                                               }"
                                                                                                                                           )
                                                                                                                                           Log.d(
                                                                                                                                               "DATOSVALIDOS",
                                                                                                                                               "R renovables: ${
                                                                                                                                                   String.format(
                                                                                                                                                       "%.2e",
                                                                                                                                                       reno2
                                                                                                                                                   )
                                                                                                                                               }"
                                                                                                                                           )
                                                                                                                                           Log.d(
                                                                                                                                               "DATOSVALIDOS",
                                                                                                                                               "R renovables: ${
                                                                                                                                                   String.format(
                                                                                                                                                       "%.2e",
                                                                                                                                                       plantingsum
                                                                                                                                                   )
                                                                                                                                               }"
                                                                                                                                           )

                                                                                                                                           Log.d(
                                                                                                                                               "DATOSVALIDOS",
                                                                                                                                               "R No Renovables: ${
                                                                                                                                                   String.format(
                                                                                                                                                       "%.2e",
                                                                                                                                                       emergia_p_f6
                                                                                                                                                   )
                                                                                                                                               }"
                                                                                                                                           )

                                                                                                                                       }


                                                                                                                                       Log.d(
                                                                                                                                           "TAG",
                                                                                                                                           "Document data: ${estateDataModel}"
                                                                                                                                       )
                                                                                                                                   } else {
                                                                                                                                       Log.d(
                                                                                                                                           "TAG",
                                                                                                                                           "Document does not exist"
                                                                                                                                       )
                                                                                                                                   }
                                                                                                                               }
                                                                                                                               .addOnFailureListener { e ->
                                                                                                                                   Log.e(
                                                                                                                                       "TAG",
                                                                                                                                       "Error getting document",
                                                                                                                                       e
                                                                                                                                   )
                                                                                                                               }
                                                                                                                       } else {
                                                                                                                           // No se encontraron documentos que coincidan con el criterio de búsqueda
                                                                                                                       }
                                                                                                                   }

                                                                                                           }


                                                                                                           Log.d(
                                                                                                               "TAG",
                                                                                                               "Document data: ${estateDataModel}"
                                                                                                           )
                                                                                                       } else {
                                                                                                           Log.d(
                                                                                                               "TAG",
                                                                                                               "Document does not exist"
                                                                                                           )
                                                                                                       }
                                                                                                   }
                                                                                                   .addOnFailureListener { e ->
                                                                                                       Log.e(
                                                                                                           "TAG",
                                                                                                           "Error getting document",
                                                                                                           e
                                                                                                       )
                                                                                                   }
                                                                                           } else {
                                                                                               // No se encontraron documentos que coincidan con el criterio de búsqueda
                                                                                           }
                                                                                       }


                                                                               } catch (e: Exception) {
                                                                                   println("Errpr get estateData: $e")
                                                                               }


                                                                           }
                                                                           suma += 1000000000000
                                                                           Log.d("sumaprueba", "suma : " + suma)
                                                                           Log.d(
                                                                               "TAG",
                                                                               "Document data: ${estateDataModel}"
                                                                           )
                                                                       } else {
                                                                           Log.d("TAG", "Document does not exist")
                                                                       }
                                                                   }.addOnFailureListener { e ->
                                                                       Log.e("TAG", "Error getting document", e)
                                                                   }

                                                           } else {
                                                               // No se encontraron documentos que coincidan con el criterio de búsqueda
                                                           }
                                                       }

                                               }


                                               Log.d("TAG", "Document data: ${estateDataModel}")
                                           } else {
                                               Log.d("TAG", "Document does not exist")
                                           }
                                       }.addOnFailureListener { e ->
                                           Log.e("TAG", "Error getting document", e)
                                       }
                               } else {
                                   // No se encontraron documentos que coincidan con el criterio de búsqueda
                               }
                           }


                   }
               }
            }
        }



    }


}