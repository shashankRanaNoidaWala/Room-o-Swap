package com.bottlerunner.room_o_swap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.databinding.FragmentSignInBinding
import com.bottlerunner.room_o_swap.databinding.FragmentSignUpBinding

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentSignInBinding>(inflater
            ,R.layout.fragment_sign_in,container,false)

        binding.btnSignInSignIn.setOnClickListener {

            Navigation.findNavController(view!!).navigate(R.id.action_signInFragment_to_homeFragment)
        }

        return binding.root
    }

}