package com.fup.accoffe.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.databinding.FragmentLoginBinding
import com.fup.accoffe.databinding.FragmentRegisterBinding
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        backResgister()
        enter()
        return root
    }
    private fun backResgister() {
        //val estateId = arguments?.getString("estateId")
        // Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnRegister.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.nav_RegisterFragment)
        }
    }
    private fun enter() {
        //val estateId = arguments?.getString("estateId")
        // Log.d("DashboardInfoFragment", "Received estateId: $estateId")

        binding.btnEnter.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.nav_home)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}