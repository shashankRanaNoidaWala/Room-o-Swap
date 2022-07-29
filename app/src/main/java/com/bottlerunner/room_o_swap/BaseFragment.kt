package com.bottlerunner.room_o_swap

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

open class BaseFragment(var layoutId: Int) : Fragment() {

    open lateinit var currContext:Context
    private lateinit var mProgressDialog:Dialog
    private var doubleBackToExitPressedOnce:Boolean =false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        currContext=context
    }

    fun showProgressDialog(text: String){                                                         //WHAT IS THISSS??
        mProgressDialog = Dialog(currContext)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.findViewById<TextView>(R.id.tv_progress_text).text = text
        mProgressDialog.show()
    }

    fun hideProgressDialog() {
        mProgressDialog.dismiss()
    }

    fun getCurrentUserID(): String{
        if(FirebaseAuth.getInstance().currentUser!=null) {
            return FirebaseAuth.getInstance().currentUser!!.uid
        }
        else{
            Toast.makeText(currContext,"Not signed in yet",Toast.LENGTH_SHORT).show()
        }
        return ""
    }


    fun showError(message: String){
        val req = activity?.findViewById<View>(android.R.id.content)
        if(req !=null) {
            val snackBar = Snackbar.make(req, message, Snackbar.LENGTH_LONG)
            snackBar.view.setBackgroundColor(
                ContextCompat.getColor(
                    currContext,
                    R.color.purple_500
                )
            )
            snackBar.setTextColor(R.color.white)
            snackBar.show()
        }
    }
}