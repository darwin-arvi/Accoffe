package com.fup.accoffe.ui.estate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentEstateBinding
import com.fup.accoffe.models.EstateModel
import com.fup.accoffe.models.HarvestModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EstateFragment : Fragment() {

    private var _binding: FragmentEstateBinding? = null
    private val db = FirebaseFirestore.getInstance()

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
        saveEstate()
        initViews()
        backEstate()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


    private fun saveEstate(){
        binding.botonSave.setOnClickListener {
            val estateCollection = db.collection("estate")
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    initViews()
                    val estate = EstateModel("",econvertionalmendra.toInt(),edolar.toInt(),ename.toString(),
                        eproductarea.toInt(),etotalarea.toInt(),etypecrop.toString(),eyear.toString())

                    val documentReference = estateCollection.add(estate).await()
                    println("Document created with ID: ${documentReference.id}")
                } catch (e: Exception) {
                    // Manejar errores si es necesario
                    e.printStackTrace()
                }
            }
        }
    }
    private fun backEstate(){
        val estateId = arguments?.getString("estateId")
        Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.botonBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("estateId", estateId)
            Navigation.findNavController(requireView()).navigate(R.id.dashboardInfoFragment,bundle)
        }
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
}