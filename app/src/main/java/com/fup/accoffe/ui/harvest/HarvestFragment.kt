package com.fup.accoffe.ui.harvest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentDryingBinding
import com.fup.accoffe.databinding.FragmentHarvestBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HarvestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HarvestFragment : Fragment() {

    private var _binding: FragmentHarvestBinding? = null
    private val binding get() = _binding!!

    private lateinit var h_year: String
    private lateinit var h_maq_man: String
    private lateinit var h_combustible: String
    private lateinit var h_transport: String
    private lateinit var l_Pjornal_recole: String

    private var transformidad_f17 = 6700000000
    private var transformidad_f18 = 111111
    private var transformidad_f19 = 22500000000000
    private var transformidad_c20 = 22500000000000
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
        _binding = FragmentHarvestBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initViews()
        return root

    }
    private fun initViews() {
        h_year = binding.etHYear.text.toString()
        h_maq_man= binding.etHMaqMan.text.toString()
        h_combustible= binding.etHCombustible.text.toString()
        h_transport= binding.etHTransport.text.toString()
        l_Pjornal_recole = binding.etLPjornalRecole.text.toString()
    }
/*
    ///operaciones
    //COSECHA F17
    val emergia_p_f17 = estateData.h_maq_man * transformidad_f17
    //COSECHA F18
    var anio_analizado_c1 = estateData.h_combustible * 39500000
    val emergia_p_f18 = anio_analizado_c1 * transformidad_f18
    //COSECHA F19
    val emergia_p_f19 = estateData.h_transport * transformidad_f19
    //COSECHA F20
    val emergia_p_f20 = estateData.l_Pjornal_recole * transformidad_c20
    //TOTAL COSECHA
    var total_c = emergia_p_f17 + emergia_p_f18 + emergia_p_f19 + emergia_p_f20

    this.estateInfo.total_c = total_c

    this.estateInfo.emergia_p_f17 = emergia_p_f17
    this.estateInfo.emergia_p_f18 = emergia_p_f18
    this.estateInfo.emergia_p_f19 = emergia_p_f19
    this.estateInfo.emergia_p_f20 = emergia_p_f20

    this.estateInfo.transformidad_f17 = transformidad_f17
    this.estateInfo.transformidad_f18 = transformidad_f18
    this.estateInfo.transformidad_f19 = transformidad_f19
    this.estateInfo.transformidad_c20 = transformidad_c20

    this.estateInfo.anio_analizado_c1 = anio_analizado_c1

},
async guardar() {
    val plantSnapShot = await db.collection('sumatoriaone').where('s_year', '==', this.estateInfo.h_year).get()
    val plants = []
    plantSnapShot.forEach((plant) => {
        val plantData = plant.data()
        plantData.id = plant.id
        plants.push(plantData)
    });
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

    this.energia.s_year = sumaData.s_year

    this.energia.emergia_p_f17 = this.estateInfo.emergia_p_f17
    this.energia.emergia_p_f18 = this.estateInfo.emergia_p_f18
    this.energia.emergia_p_f19 = this.estateInfo.emergia_p_f19
    this.energia.emergia_p_f20 = this.estateInfo.emergia_p_f20

    this.update()
    alert("Saving")

},
async update() {
    await setDoc(this.docRefe, this.energia) ////comparar codigo con la tabla de plantacion
    this.$router.go(-1)
}
/* async update() {
  alert("Saving");
  const addedDoc = await setDoc(sumatoriaoneColl, this.energia);
  console.log(addedDoc);
  this.$router.go(-1);
} */
},
created() {
    val infoId = this.$route.params.infoId
    this.infoId = infoId
    this.getEstate()
},
*/
}