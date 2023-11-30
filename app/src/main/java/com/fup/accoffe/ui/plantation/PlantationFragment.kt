package com.fup.accoffe.ui.plantation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentPlantationBinding

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

    private var transformidad = 1
    private var transformidad_f2 = 2520
    private var transformidad_f3 = 30600
    private var transformidad_f4 = 17600
    private var transformidad_f5 = 39800
    private var transformidad_f6 = 74000
    private var transformidad_f7 = 6620000000
    private var transformidad_f8 = 9350000000
    private var transformidad_f9 = 932000000
    private var transformidad_f10 = 6620000000
    private var transformidad_f11 = 1680000000
    private var transformidad_f12 = 3870000000
    private var transformidad_f13 = 58500
    private var transformidad_f14 = 6700000000
    private var transformidad_f15 = 22500000000000
    private var transformidad_f16 = 14800000000
    private var ins_m2 = 3600000

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
        // Inflate the layout for this fragment
        _binding = FragmentPlantationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        return root
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