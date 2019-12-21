package com.atm1504.gosocio.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.atm1504.gosocio.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val PREFS_NAME = "atm"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy data storing. Would be actually present in logi page, after the api is ready
        val sharedPref: SharedPreferences = requireContext().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString("name","Atreyee Mukherjee")
        editor.putString("email","me@atm1504.in")
        editor.putString("phone","8967570983")
        editor.putFloat("coins", 50.0F)
        editor.putInt("stick1",2)
        editor.putInt("stick2",1)
        editor.putInt("stick3",0)
        editor.putInt("stick4",7)
        editor.putInt("stick5",9)
        editor.putBoolean("loggedIn",true)
        editor.commit()

    }
}