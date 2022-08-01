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

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var binding:FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.btnAddRequest.setOnClickListener{
            Navigation.findNavController(view!!).navigate(R.id.action_homeFragment_to_addRequestFragment)

        }

        return binding.root
    }

    override fun onStart() {

        showProgressDialog("Loading requests please wait")
        FirebaseFirestore.getInstance().collection("users")
            .get().addOnCompleteListener{
                    it->
                if(it.isSuccessful){                                                    //TODO: oh git!, unable to deserialise yet again
                    Database.userList = it.result.toObjects<UserApna>().toMutableList()
                    for( i in Database.userList){
                        if(i.requestList.size !=0){
                            for(j in i.requestList)
                                Database.requestList.add(j)
                        }
                    }

                    binding.rvMatchesAvailable.adapter = RequestAdapter(currContext, Database.requestList)

                    binding.rvMatchesAvailable.layoutManager = LinearLayoutManager(currContext)


                }
                else{
                    Toast.makeText(MainActivity(),it.exception.toString(), Toast.LENGTH_SHORT).show()
                }

                hideProgressDialog()

            }

        super.onStart()
    }


}