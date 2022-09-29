package com.bottlerunner.room_o_swap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.databinding.FragmentSignInBinding
import com.bottlerunner.room_o_swap.databinding.FragmentSignUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private lateinit var email:String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentSignInBinding>(inflater,R.layout.fragment_sign_in,container,false)

        binding.btnSignInSignIn.setOnClickListener {
            email=binding.tietEmailSignIn.text.toString()
            password=binding.tietPasswordSignIn.text.toString()
            if(validateForm(email,password)){
                signInUser(email,password)
            }

        }

        return binding.root
    }

    private fun signInUser(email: String, password: String){
        Toast.makeText(currContext,"Loading...",
            Toast.LENGTH_LONG).show()
        showProgressDialog("Thamba thamba")
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener {      //This is lamda function
            hideProgressDialog()                                                                                        //Know how to make it a regular one
            if (it.isSuccessful) {
                val firebaseUser = it.result!!.user!!
                val registeredEmail = firebaseUser.email!!
                Toast.makeText(
                    currContext,
                    "${firebaseUser.email} you have successfully logged in",
                    Toast.LENGTH_SHORT
                ).show()
                Navigation.findNavController(view!!).navigate(R.id.action_signInFragment_to_homeFragment)

            } else {
                Toast.makeText(currContext, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(email: String, password: String): Boolean{

        if(email.isEmpty()){
            showError("Please enter a valid email")
            return true
        }
        if(password.isEmpty()){
            showError("Please enter a valid password")
            return false
        }
        return true

    }

}