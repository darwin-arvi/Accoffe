package com.fup.accoffe.ui.pre_processing

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentHarvestInfoBinding
import com.fup.accoffe.databinding.FragmentPreProcessingBinding
import com.fup.accoffe.databinding.FragmentPreProcessingInfoBinding
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
 * Use the [PreProcessingInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PreProcessingInfoFragment : Fragment() {

    private var _binding: FragmentPreProcessingInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private val db = FirebaseFirestore.getInstance()

    val v_dolar2014 = 2000.0
    val transformidad_t1 = 26500000000000.0
    val transformidad_t2 = 22500000000000.0
    val transformidad_t5 = 23100000000.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreProcessingInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        backPreProcessingInfo()
        energyModel = EnergyModel()
        getStateData()

        return root
    }
    private fun backPreProcessingInfo() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }
    private fun getStateData(){

        val PreProcessingId = arguments?.getString("preProcessingid")
        Log.d("getStateData", "Received estateId: $PreProcessingId")

        db.collection("beaten").document(PreProcessingId!!).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {

                    val info = documentSnapshot.toObject(EstateDataModel::class.java)
                    if (info != null) {
                        estateDataModel = info

                        ///operaciones

                        var flujo1 = ((estateDataModel.b_val_maq!! / v_dolar2014) / 30) / 118
                        val t1 = (flujo1 * transformidad_t1)

                        this.estateInfoModel.flujo1 = flujo1;
                        this.estateInfoModel.t1 = t1;

                        //const flujo2 = 14244 / 118;
                        val flujo2 = estateDataModel.b_gf_Benef!! / 118;
                        var t2 = (flujo2 * transformidad_t2);

                        this.estateInfoModel.flujo2 = flujo2;
                        this.estateInfoModel.t2 = t2;

                        val flujo3 = ((estateDataModel.b_val_infra!! / v_dolar2014) / 50) / 118;
                        var t3 = (flujo3 * transformidad_t1);

                        this.estateInfoModel.flujo3 = flujo3;
                        this.estateInfoModel.t3 = t3;

                        val flujo4 = ((estateDataModel.b_promed_electrico!! * 12) / estateDataModel.b_dolar!!) / 118;
                        var t4 = (flujo4 * transformidad_t1);

                        this.estateInfoModel.flujo4 = flujo4;
                        this.estateInfoModel.t4 = t4;

                        var t5 = (estateDataModel.b_num_costales!! * transformidad_t5);
                        this.estateInfoModel.t5 = t5;

                        this.estateInfoModel.total=t1+t2+t3+t4+t5

                        this.estateInfoModel.b_num_costales = estateDataModel.b_num_costales
                        this.estateInfoModel.b_promed_electrico = estateDataModel.b_promed_electrico
                        this.estateInfoModel.b_gf_Benef = estateDataModel.b_gf_Benef
                        this.estateInfoModel.b_val_infra = estateDataModel.b_val_infra
                        this.estateInfoModel.b_year = estateDataModel.b_year
                        this.estateInfoModel.b_val_maq = estateDataModel.b_val_maq
                        this.estateInfoModel.transformidad_t1 = transformidad_t1
                        this.estateInfoModel.transformidad_t2 = transformidad_t2
                        this.estateInfoModel.transformidad_t5 = transformidad_t5
                        val dolar = estateDataModel.b_dolar

                        binding.item12.text=String.format("%.2e",estateInfoModel.flujo1)
                        binding.item13.text =String.format("%.2e",estateInfoModel.transformidad_t1)
                        binding.item14.text =String.format("%.2e",estateInfoModel.t1)

                        binding.item22.text=String.format("%.2e",estateInfoModel.flujo2)
                        binding.item23.text =String.format("%.2e",estateInfoModel.transformidad_t2)
                        binding.item24.text =String.format("%.2e",estateInfoModel.t2)

                        binding.item32.text=String.format("%.2e",estateInfoModel.flujo3)
                        binding.item33.text =String.format("%.2e",estateInfoModel.transformidad_t1)
                        binding.item34.text =String.format("%.2e",estateInfoModel.t3)

                        binding.item42.text=String.format("%.2e",estateInfoModel.flujo4)
                        binding.item43.text =String.format("%.2e",estateInfoModel.transformidad_t1)
                        binding.item44.text =String.format("%.2e",estateInfoModel.t4)

                        binding.item52.text=String.format("%.2e",estateInfoModel.b_num_costales)
                        binding.item53.text =String.format("%.2e",estateInfoModel.transformidad_t5)
                        binding.item54.text =String.format("%.2e",estateInfoModel.t5)

                        binding.item61.text =String.format("%.2e",estateInfoModel.total)

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
