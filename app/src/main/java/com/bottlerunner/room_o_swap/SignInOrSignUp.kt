package com.bottlerunner.room_o_swap

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.databinding.FragmentSignInOrSignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth


class SignInOrSignUp : Fragment() {

    private lateinit var binding:FragmentSignInOrSignUpBinding
    private lateinit var currContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseAuth.getInstance().signOut()

        binding = DataBindingUtil.inflate(inflater
            ,R.layout.fragment_sign_in_or_sign_up,container,false)

//        if(FirebaseAuth.getInstance().currentUser!= null){
//            Navigation.findNavController(view!!).navigate(R.id.action_signInOrSignUp_to_homeFragment)
//        }

        binding.btnSignUp.setOnClickListener {

            if(view!= null){

                Navigation.findNavController(view!!).navigate(R.id.action_signInOrSignUp_to_signUpFragment)
            }
            else{
                binding.btnSignUp.text="No view found"
            }
        }

        binding.btnSignIn.setOnClickListener {

            if(view!= null){

                Navigation.findNavController(view!!).navigate(R.id.action_signInOrSignUp_to_signInFragment)
            }
            else{
                binding.btnSignUp.text="No view found"
            }
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        currContext=context
    }
}