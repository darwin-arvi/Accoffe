package com.fup.accoffe.ui.harvest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentHarvestBinding
import com.fup.accoffe.databinding.FragmentHarvestInfoBinding
import com.fup.accoffe.models.EnergyModel
import com.fup.accoffe.models.EstateDataModel
import com.fup.accoffe.models.EstateInfoModel
import com.google.firebase.firestore.FirebaseFirestore

class HarvestInfoFragment : Fragment() {



    private lateinit var viewModel: HarvestInfoViewModel
    private var _binding: FragmentHarvestInfoBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private lateinit var estateInfoModel: EstateInfoModel
    private lateinit var energyModel: EnergyModel
    private lateinit var estateDataModel: EstateDataModel

    private var transformidad_f17 = 6700000000.0
    private var transformidad_f18 = 111111.0
    private var transformidad_f19 = 22500000000000.0
    private var transformidad_c20 = 22500000000000.0
    private var anio_analizado_c1 = 1.0 // le asigne un valor para que no genere error


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHarvestInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        estateInfoModel = EstateInfoModel()
        estateDataModel = EstateDataModel()
        backHarvestInfo()
        energyModel = EnergyModel()
        getStateData()

        return root

    }
    private fun backHarvestInfo() {
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnback1.setOnClickListener {
            Navigation.findNavController(requireView()).popBackStack()
        }
    }
    private fun getStateData(){

        val harvestId = arguments?.getString("harvestid")
        Log.d("getStateData", "Received estateId: $harvestId")

        db.collection("harvest").document(harvestId!!).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {

                    val info = documentSnapshot.toObject(EstateDataModel::class.java)
                    if (info != null) {
                        estateDataModel = info


                        //asiganmos constantes al objeto

                        this.estateInfoModel.transformidad_f17 = transformidad_f17
                        this.estateInfoModel.transformidad_f18 = transformidad_f18
                        this.estateInfoModel.transformidad_f19 = transformidad_f19
                        this.estateInfoModel.transformidad_c20 = transformidad_c20
                        this.estateInfoModel.anio_analizado_c1 = anio_analizado_c1

                        //procedemos con las operaciones
                        ///operaciones
                        //COSECHA F17
                        val emergia_p_f17 = estateDataModel.h_maq_man!! * transformidad_f17
                        //COSECHA F18
                        var anio_analizado_c1 = estateDataModel.h_combustible!! * 39500000
                        val emergia_p_f18 = anio_analizado_c1 * transformidad_f18
                        //COSECHA F19
                        val emergia_p_f19 = estateDataModel.h_transport!! * transformidad_f19
                        //COSECHA F20
                        val emergia_p_f20 = estateDataModel.l_Pjornal_recole!! * transformidad_c20
                        //TOTAL COSECHA
                        var total_c = emergia_p_f17 + emergia_p_f18 + emergia_p_f19 + emergia_p_f20

                        this.estateInfoModel.total_c = total_c

                        this.energyModel.emergia_p_f17 = emergia_p_f17
                        this.energyModel.emergia_p_f18 = emergia_p_f18
                        this.energyModel.emergia_p_f19 = emergia_p_f19
                        this.energyModel.emergia_p_f20 = emergia_p_f20

                        this.estateInfoModel.transformidad_f17 = transformidad_f17
                        this.estateInfoModel.transformidad_f18 = transformidad_f18
                        this.estateInfoModel.transformidad_f19 = transformidad_f19
                        this.estateInfoModel.transformidad_c20 = transformidad_c20

                        this.estateInfoModel.anio_analizado_c1 = anio_analizado_c1
                        Log.d("harvestable", "datos solicitados: ${estateInfoModel.transformidad_f17} ${estateDataModel.h_maq_man} ${energyModel.emergia_p_f17}")


                        binding.tvflujoanual.text=estateDataModel.h_maq_man.toString()
                        binding.item13.text =estateInfoModel.transformidad_f17.toString()
                        binding.item14.text =energyModel.emergia_p_f17.toString()

                        binding.item22.text=estateInfoModel.anio_analizado_c1.toString()
                        binding.item23.text =estateInfoModel.transformidad_f18.toString()
                        binding.item24.text =energyModel.emergia_p_f18.toString()

                        binding.item32.text=estateInfoModel.h_transport.toString()
                        binding.item33.text =estateInfoModel.transformidad_f19.toString()
                        binding.item34.text =energyModel.emergia_p_f19.toString()

                        binding.item42.text=estateInfoModel.l_Pjornal_recole.toString()
                        binding.item43.text =estateInfoModel.transformidad_c20.toString()
                        binding.item44.text =energyModel.emergia_p_f20.toString()

                        binding.item51.text =estateInfoModel.total_c.toString()

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