package com.bottlerunner.room_o_swap

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bottlerunner.room_o_swap.data.MyRequestAdapter
import com.bottlerunner.room_o_swap.data.RequestAdapter
import com.bottlerunner.room_o_swap.databinding.FragmentMyRequestsBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.zip.Inflater

class MyRequestsFragment : BaseFragment(R.layout.fragment_my_requests) {

    private lateinit var binding:FragmentMyRequestsBinding

      override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          val binding = DataBindingUtil.inflate<FragmentMyRequestsBinding>(inflater,R.layout.fragment_my_requests
              ,container,false)


          FirebaseAuth.getInstance().currentUser?.
          let{
              val currUser = Database.findUserById(it.uid)
              binding.rvYourRequests.adapter = MyRequestAdapter(currContext, currUser!!.requestList,currUser, currUser)
              binding.rvYourRequests.layoutManager = LinearLayoutManager(currContext)
          }

          return binding.root
    }

    override fun onStart() {

        binding = DataBindingUtil.getBinding(view!!)!!

        FirebaseAuth.getInstance().currentUser?.
        let{
            val currUser1 = Database.findUserById(it.uid)
            val currUser2 =Database.findUserById(it.uid)

            binding.rvYourRequests.adapter = MyRequestAdapter(currContext, currUser1!!.requestList,currUser1,currUser2!!)
            binding.rvYourRequests.layoutManager = LinearLayoutManager(currContext)
        }

        super.onStart()
    }

}