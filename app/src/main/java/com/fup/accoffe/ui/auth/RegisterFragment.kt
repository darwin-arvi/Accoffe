package com.fup.accoffe.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentLoginBinding
import com.fup.accoffe.databinding.FragmentRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val root: View = binding.root
        backResgister()

        return root
    }
    private fun backResgister() {
        //val estateId = arguments?.getString("estateId")
        // Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.nav_LoginFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}