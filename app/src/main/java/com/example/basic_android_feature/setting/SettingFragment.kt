package com.example.basic_android_feature.setting


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.basic_android_feature.R
import com.example.basic_android_feature.home.HomeActivity
import com.example.basic_android_feature.register.RegisterActivity
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

    private lateinit var settingViewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        settingViewModel = (activity as HomeActivity).settingViewModel

        btnUnregister.setOnClickListener {
            settingViewModel.unRegisterUser()
            settingViewModel.selectUserList()
            observeUserListOfFragment()
        }
    }

    private fun observeUserListOfFragment() {
        settingViewModel.observeUserList()
            ?.observe(viewLifecycleOwner, androidx.lifecycle.Observer { userList ->
                if (userList.isEmpty()) {
                    startActivity(Intent(activity, RegisterActivity::class.java))
                    activity?.finish()
                }
            })
    }

}
