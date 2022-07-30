package com.bottlerunner.room_o_swap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.databinding.FragmentAddRequestBinding
import com.google.firebase.auth.FirebaseAuth

class AddRequestFragment : BaseFragment(R.layout.fragment_add_request) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentAddRequestBinding>(inflater
            ,R.layout.fragment_add_request,container,false)

        if(FirebaseAuth.getInstance().currentUser == null){
            makeText(currContext,"currUser is null",Toast.LENGTH_SHORT).show()
        }

        binding.fabAdd.setOnClickListener{

            var selectedHostel = binding.sToHostel.selectedItem.toString()
            var roomRange:IntRange = IntRange(binding.rangeSlider.valueFrom.toInt(),binding.rangeSlider.valueTo.toInt())


            var currUser = FirebaseAuth.getInstance().currentUser?.uid?.let { it1 ->
                Database.findUserById(it1)}
            var newRequest = currUser?.let { it1 ->
                Request(
                    it1.hostel,
                    it1.roomNo,
                    Pair(selectedHostel, roomRange),
                    it1.id,
                    it1.name
                )
            }

            if (newRequest != null) {
                Database.requestList.add(newRequest)
            }
            else{
                Toast.makeText(currContext,"new request is null",Toast.LENGTH_SHORT).show()
            }

            newRequest?.let { it1 -> currUser?.requestList?.add(it1) }

            Navigation.findNavController(view!!).navigate(R.id.action_addRequestFragment_to_homeFragment)

        }

        return binding.root
    }

}