package com.bottlerunner.room_o_swap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bottlerunner.room_o_swap.data.RequestAdapter
import com.bottlerunner.room_o_swap.data.UserApna
import com.bottlerunner.room_o_swap.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
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

        var text1:String =""
        var currUserId:String =FirebaseAuth.getInstance().currentUser?.uid!!
        Database.findUserById(currUserId)?. let{
            it->
            for(i in it.requestList){
                text1 = text1 + i.toHostel + "\n \n"
            }

            binding.tvToCardHome.text = text1
            binding.tvFromCardHome.text = it.hostel
        }

        binding.swipeRefresh.setOnRefreshListener {

            var text1:String =""
            var currUserId:String =FirebaseAuth.getInstance().currentUser?.uid!!
            Database.findUserById(currUserId)?. let{
                    it->
                for(i in it.requestList){
                    text1 = text1 + i.toHostel + "\n \n"
                }

                binding.tvToCardHome.text = text1
                binding.tvFromCardHome.text = it.hostel
            }                           //puts stuff in your matches

            showProgressDialog("Loading requests please wait")
            FirebaseFirestore.getInstance().collection("users")         //gets the latest data and puts it in rv
                .get().addOnCompleteListener { it ->
                    if (it.isSuccessful) {                                                    //TODO: oh git!, unable to deserialise yet again
                        Database.userList = it.result.toObjects<UserApna>().toMutableList()
                        for (i in Database.userList) {
                            if (i.requestList.size != 0) {
                                for (j in i.requestList)
                                    Database.requestList.add(j)
                            }
                        }

                        binding.rvMatchesAvailable.adapter =
                            RequestAdapter(currContext, Database.requestList)

                        binding.rvMatchesAvailable.layoutManager = LinearLayoutManager(currContext)


                    } else {
                        Toast.makeText(MainActivity(), it.exception.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }

                    hideProgressDialog()
                }

            var matchList: MutableList<Request> = mutableListOf()
            FirebaseAuth.getInstance().currentUser?.let {
                var currUser = Database.findUserById(it.uid)
                if (currUser == null) {
                    Toast.makeText(
                        currContext,
                        "Current user not found in database",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    matchList = Database.makeMatchList(currUser)
                }
            }                                           //adding stuff in your matches
            binding.rvYourMatches.adapter = MatchAdapter(currContext, matchList)
            binding.rvYourMatches.layoutManager = LinearLayoutManager(currContext)

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
        }                                   //getting latest data and rv stuff

        var matchList: MutableList<Request> = mutableListOf()
        FirebaseAuth.getInstance().currentUser?.let {
            var currUser = Database.findUserById(it.uid)
            if (currUser == null) {
                Toast.makeText(
                    currContext,
                    "Current user not found in database",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                matchList = Database.makeMatchList(currUser)
            }
        }                                           //adding stuff in your matches

        binding.rvYourMatches.adapter = MatchAdapter(currContext, matchList)
        binding.rvYourMatches.layoutManager = LinearLayoutManager(currContext)
        super.onStart()
    }






}