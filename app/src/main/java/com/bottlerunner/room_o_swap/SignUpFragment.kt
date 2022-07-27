package com.bottlerunner.room_o_swap

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.databinding.FragmentHomeBinding
import com.bottlerunner.room_o_swap.databinding.FragmentSignInOrSignUpBinding
import com.bottlerunner.room_o_swap.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var currContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater
            ,R.layout.fragment_sign_up,container,false)

        binding.btnSignUpSignUpFrag.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_signUpFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        currContext=context
        super.onAttach(context)
    }


}