package com.bottlerunner.room_o_swap

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bottlerunner.room_o_swap.Database.userList
import com.bottlerunner.room_o_swap.databinding.FragmentHomeBinding
import com.bottlerunner.room_o_swap.databinding.FragmentSignInOrSignUpBinding
import com.bottlerunner.room_o_swap.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.bottlerunner.room_o_swap.data.UserApna
import com.bottlerunner.room_o_swap.data.model.FirestoreClass
import com.google.firebase.firestore.FirebaseFirestore

class SignUpFragment : BaseFragment(R.layout.fragment_sign_up) {

    override lateinit var currContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentSignUpBinding>(inflater
            ,R.layout.fragment_sign_up,container,false)

        binding.btnSignUpSignUpFrag.setOnClickListener {
            var name = binding.tietNameSignUp.text.toString()
            var email =binding.tietEmailSignUp.text.toString()
            var password = binding.tietPasswordSignUp.text.toString()
            var hostel = binding.sHostel.selectedItem.toString()
            var roomNo = binding.tietRoomNo.text.toString().toInt()

            if(validateForm(name, email, password,roomNo)){
                signUpUser(name,email,password,hostel,roomNo)
                Navigation.findNavController(view!!).navigate(R.id.action_signUpFragment_to_homeFragment)
            }

        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        currContext=context
        super.onAttach(context)
    }

    fun signUpUser(name:String, email: String, password: String, hostel:String,roomNo: Int) {
        Toast.makeText(
            currContext,
            "Darling darling stand, stand by me, stand, stand by, stand by me, stand by me, stand by me",
            Toast.LENGTH_LONG
        ).show()
        showProgressDialog("Thamba thamba")
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {      //This is lamda function
                hideProgressDialog()                                                                                        //Know how to make it a regular one
                if (it.isSuccessful) {
                    val firebaseUser = it.result!!.user!!
                    val registeredEmail = firebaseUser.email!!
                    Toast.makeText(
                        currContext,
                        "${firebaseUser.email} you have successfully signed un",
                        Toast.LENGTH_SHORT
                    ).show()

                    val currUser = UserApna(firebaseUser.uid,name, email,password, hostel, roomNo, mutableListOf())

                    FirebaseFirestore.getInstance().collection("users")
                        .document(firebaseUser.uid).set(currUser).addOnSuccessListener {
                            Toast.makeText(currContext,"Added successfully",Toast.LENGTH_SHORT).show()
                        }
                }

                else{
                    Toast.makeText(currContext, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateForm(name:String,email: String, password: String,roomNo: Int): Boolean{

        if(name.length == 0){
            showError("Please enter a valid email")
            return true
        }

        if(email.length == 0){
            showError("Please enter a valid email")
            return true
        }
        if(password.length == 0){
            showError("Please enter a valid password")
            return false
        }
        if(roomNo<1 || roomNo>1000){
            showError("Try living inside the hostel")
            return false
        }
        return true

    }


}