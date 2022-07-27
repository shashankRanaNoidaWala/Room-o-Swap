package com.bottlerunner.room_o_swap

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bottlerunner.room_o_swap.databinding.FragmentHomeBinding

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

        return binding.root
    }

    override fun onAttach(context: Context) {
        currContext = context
        super.onAttach(context)
    }

}