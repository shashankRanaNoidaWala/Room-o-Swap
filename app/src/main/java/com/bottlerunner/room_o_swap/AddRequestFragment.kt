package com.bottlerunner.room_o_swap

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.data.UserApna
import com.bottlerunner.room_o_swap.databinding.FragmentAddRequestBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class AddRequestFragment : BaseFragment(R.layout.fragment_add_request) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val binding = DataBindingUtil.inflate<FragmentAddRequestBinding>(
            inflater, R.layout.fragment_add_request, container, false
        )

        var currUserId = FirebaseAuth.getInstance().currentUser?.uid
        var currUser: UserApna?

        currUserId?.let { it1 ->
            Log.d("DebugRequestGenerated", "Request generated line 49")
            FirebaseFirestore.getInstance().collection("users")
                .document(it1).get().addOnCompleteListener { it2 ->
                    if (it2.isSuccessful) {
                        currUser = it2.result.toObject<UserApna>()
                        binding.tvIamAt.text = currUser?.name!!
                    }
                }
        }

        if (FirebaseAuth.getInstance().currentUser == null) {
            makeText(currContext, "currUser is null", Toast.LENGTH_SHORT).show()
        }
        Log.d("DebugRequestGenerated","Request generated line 33")

        binding.fabAdd.setOnClickListener {

            Log.d("DebugRequestGenerated","Request generated line 36")


            var selectedHostel = binding.sToHostel.selectedItem.toString()
            var roomRange: Pair<Int, Int> =
                Pair(binding.rangeSlider.valueFrom.toInt(), binding.rangeSlider.valueTo.toInt())


            var currUserId = FirebaseAuth.getInstance().currentUser?.uid
            var currUser: UserApna?

            currUserId?.let { it1 ->
                Log.d("DebugRequestGenerated","Request generated line 49")
                FirebaseFirestore.getInstance().collection("users")
                    .document(it1).get().addOnCompleteListener { it2 ->
                        if (it2.isSuccessful) {
                            currUser = it2.result.toObject<UserApna>()
                            Log.d("DebugRequestGenerated","Request generated line 54")

                            currUser?.let {                             //had a curr user, added request
                                it.requestList.add(
                                    Request(
                                        it.hostel,it.roomNo,selectedHostel,roomRange.first,roomRange.second,it.id,it.name,it.phoneNo
                                    )
                                )

                                Log.d("DebugRequestGenerated","Request generated line 63")

                                FirebaseFirestore.getInstance().collection("users")
                                    .document(it1).set(it)
                                    .addOnCompleteListener { it3 ->       //updated the user

                                        if (it3.isSuccessful) {
                                            Toast.makeText(
                                                currContext,
                                                "Request added successfully",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                currContext,
                                                it3.exception.toString(),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        } else {
                            Toast.makeText(
                                currContext,
                                it2.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
            Navigation.findNavController(view!!)
                .navigate(R.id.action_addRequestFragment_to_homeFragment)
        }

        return binding.root
    }
}