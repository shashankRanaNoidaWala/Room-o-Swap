package com.bottlerunner.room_o_swap

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
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

    private lateinit var auth: FirebaseAuth
    private lateinit var email:String
    private lateinit var password: String
    private lateinit var name :String
    private lateinit var hostel: String
    private var roomNo: Int = 0
    private lateinit var tietName:TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater
            ,R.layout.fragment_sign_in,container,false)

        binding.btnSignUpSignUpFrag.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_signInFragment_to_homeFragment)
        }

        binding.btnSignUpSignUpFrag.setOnClickListener{
            email = binding.tietEmail.toString()
            password= binding.textInputLayout2.toString()
            name =binding.tietName.text.toString()
            if(validateForm(email,password)){
                signInUser(email, password)
            }
        }

        return binding.root
    }

    fun signInUser(email: String, password: String){
        Toast.makeText(currContext,"Darling darling stand, stand by me, stand, stand by, stand by me, stand by me, stand by me",
            Toast.LENGTH_LONG).show()
        showProgressDialog("Thamba thamba")
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {      //This is lamda function
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

    private fun validateForm(name: String, email: String, password: String): Boolean{
        if(name.length == 0){
            showError("Please enter a valid name")
            return false
        }
        if(email.length == 0){
            showError("Please enter a valid email")
            return true
        }
        if(password.length == 0){
            showError("Please enter a valid password")
            return false
        }
        return true

    }

}