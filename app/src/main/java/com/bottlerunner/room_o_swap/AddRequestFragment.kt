package com.bottlerunner.room_o_swap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bottlerunner.room_o_swap.databinding.FragmentAddRequestBinding

class AddRequestFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAddRequestBinding>(inflater
            ,R.layout.fragment_add_request,container,false)
//
//        binding.fabAdd.setOnClickListener{
//
//            var selectedHostel = binding.sToHostel.selectedItem.toString()
//            var roomRange:IntRange = binding.rangeSlider.values
//
//        }

        return binding.root
    }

}