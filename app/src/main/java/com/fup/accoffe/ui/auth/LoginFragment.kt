package com.fup.accoffe.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.fup.accoffe.R
import com.fup.accoffe.dataStore
import com.fup.accoffe.databinding.FragmentLoginBinding
import com.fup.accoffe.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
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
        auth = Firebase.auth
        desplazResgister()
        enter()
        emailAndPasswordLogin()
        return root
    }
    private suspend fun savepref(uid:String){
        context?.dataStore?.edit { preferences->
            preferences[stringPreferencesKey("uid")]=uid
        }
    }
    private fun emailAndPasswordLogin(){

        binding.btnEnter.setOnClickListener {
            val email=binding.txUsuario.text.toString()
            val password=binding.txPassword.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                binding.btnEnter.isEnabled=false
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("login", "signInWithEmail:success")
                        lifecycleScope.launch(Dispatchers.IO) {
                            savepref(auth.currentUser?.uid.toString())
                            Log.d(
                                "emailAndPasswordLogin",
                                "emailAndPasswordLogin: " + auth.currentUser?.uid
                            )
                            activity?.runOnUiThread {
                                Navigation.findNavController(requireView()).navigate(R.id.nav_home)
                            }
                        }
                    } else {
                        binding.btnEnter.isEnabled=true
                        Toast.makeText(requireContext(), "Error al Iniciar sesion", Toast.LENGTH_SHORT).show()
                        Log.d("login", "signInWithEmail:Error")
                    }
                }
            }else{
                Toast.makeText(requireContext(), "Revisa los campos", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun desplazResgister() {
        binding.btnRegister.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.nav_RegisterFragment)
        }
    }
    private fun enter() {
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