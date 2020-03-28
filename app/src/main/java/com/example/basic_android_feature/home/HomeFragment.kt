package com.example.basic_android_feature.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.basic_android_feature.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var userProfileViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        userProfileViewModel.setUserLiveData()

        userProfileViewModel.observeUserLiveData().observe(viewLifecycleOwner, Observer { user ->
            Log.i("-----> ", "Name : " + user.name)
            Log.i("-----> ", "Age : " + user.age)
            Log.i("-----> ", "Place : " + user.location)

            tvName.text = user.name
            tvAge.text = user.age.toString()
            tvPlace.text = user.location

        })
    }

}
