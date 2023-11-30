package com.fup.accoffe.ui.pre_processing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentPreProcessingBinding

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

    private lateinit var b_year: String
    private lateinit var b_val_maq: String
    private lateinit var b_dolar: String
    private lateinit var b_val_infra: String
    private lateinit var b_promed_electrico: String
    private lateinit var b_num_costales: String
    private lateinit var beneficios_por_año: String

    private var v_dolar2014 = 2000
    private var transformidad_t1 = 26500000000000
    private var transformidad_t2 = 22500000000000
    private var transformidad_t5 = 23100000000


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
        _binding = FragmentPreProcessingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        return root
    }
    private fun initViews() {
        b_year= binding.etBYear.text.toString()
        b_val_maq= binding.etBValMaq.text.toString()
        b_dolar= binding.etBDolar.text.toString()
        b_val_infra= binding.etBValInfra.text.toString()
        b_promed_electrico= binding.etBPromedElectrico.text.toString()
        b_num_costales= binding.etBNumCostales.text.toString()
        beneficios_por_año= binding.etBeneficiosPorAO.text.toString()
    }

/*
///operaciones
    private var flujo1 = ((estateData.b_val_maq / v_dolar2014) / 30) / 118
    val t1 = (flujo1 * transformidad_t1)

    this.estateInfo.flujo1 = flujo1
    this.estateInfo.t1 = t1

    //const flujo2 = 14244 / 118;
    private var flujo2 = estateData.b_gf_Benef / 118
    val t2 = (flujo2 * transformidad_t2)

    this.estateInfo.flujo2 = flujo2
    this.estateInfo.t2 = t2

    private var flujo3 = ((estateData.b_val_infra / v_dolar2014) / 50) / 118
    val t3 = (flujo3 * transformidad_t1)

    this.estateInfo.flujo3 = flujo3
    this.estateInfo.t3 = t3

    const flujo4 = ((estateData.b_promed_electrico * 12) / dolar) / 118
    val t4 = (flujo4 * transformidad_t1)

    this.estateInfo.flujo4 = flujo4
    this.estateInfo.t4 = t4

    let t5 = (estateData.b_num_costales * transformidad_t5)
    this.estateInfo.t5 = t5

    private var totales = Number.parseInt(t1 + t2 + t3 + t4 + t5)
    this.estateInfo.total = totales
},
async guardar() {
    val plantSnapShot = await db.collection('sumatoriaone').where('s_year', '==', this.estateInfo.b_year).get()
    val plants = []
    plantSnapShot.forEach((plant) => {
        val plantData = plant.data()
        plantData.id = plant.id
        plants.push(plantData)
    })
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
    this.energia.emergia_s1 = sumaData.emergia_s1
    this.energia.emergia_s2 = sumaData.emergia_s2
    this.energia.emergia_s3 = sumaData.emergia_s3
    this.energia.emergia_s4 = sumaData.emergia_s4
    this.energia.emergia_s5 = sumaData.emergia_s5
    this.energia.emergia_s6 = sumaData.emergia_s6
    this.energia.emergia_s7 = sumaData.emergia_s7
    this.energia.emergia_s8 = sumaData.emergia_s8
    this.energia.emergia_s9 = sumaData.emergia_s9
    this.energia.emergia_s30 = sumaData.emergia_s30

    this.energia.s_year = sumaData.s_year

    this.energia.emergia_t1 = this.estateInfo.t1
    this.energia.emergia_t2 = this.estateInfo.t2
    this.energia.emergia_t3 = this.estateInfo.t3
    this.energia.emergia_t4 = this.estateInfo.t4
    this.energia.emergia_t5 = this.estateInfo.t5

    this.update()
    alert("Saving")
},
async update() {
    await setDoc(this.docRefe, this.energia)
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