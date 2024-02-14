package com.fup.accoffe.ui.auth

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth
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
        auth = Firebase.auth
        register()
        backResgister()

        return root
    }
    private suspend fun savepref(uid:String){
        context?.dataStore?.edit { preferences->
            preferences[stringPreferencesKey("uid")]=uid
        }
    }
    private fun register(){

        binding.btnRegister.setOnClickListener {
            // binding.txUsuario
            val email = binding.txcorreo.text.toString()
            val password = binding.txPassword.text.toString()
            if (!email.isEmpty()&&!password.isEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("registerdone", "createUserWithEmail:success")
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
                        val user = auth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("registerdone", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(requireContext(), "El registro fallo!", Toast.LENGTH_SHORT,)
                            .show()

                    }
                }
            }else{
                Toast.makeText(requireContext(), "Revisa los campos no pueden estar vacios", Toast.LENGTH_SHORT).show()
            }
        }





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