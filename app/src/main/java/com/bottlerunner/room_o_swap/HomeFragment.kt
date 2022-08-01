package com.bottlerunner.room_o_swap

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bottlerunner.room_o_swap.data.UserApna
import com.bottlerunner.room_o_swap.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var currContext:Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.rvMatchesAvailable.adapter = RequestAdapter(currContext, Database.requestList)

        binding.rvMatchesAvailable.layoutManager = LinearLayoutManager(currContext)

        binding.btnAddRequest.setOnClickListener{
            Navigation.findNavController(view!!).navigate(R.id.action_homeFragment_to_addRequestFragment)

        }

        FirebaseFirestore.getInstance().collection("users")
            .get().addOnCompleteListener{
                it->
            if(it.isSuccessful){                                                    //TODO: oh git!, unable to deserialise yet again
//                Database.userList = it.result.toObjects<UserApna>().toMutableList()
//                binding.tvRvHeader.text = Database.userList.toString()
            }
            else{
                Toast.makeText(context,it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        currContext = context
        super.onAttach(context)
    }

}