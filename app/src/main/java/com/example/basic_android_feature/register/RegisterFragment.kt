package com.example.basic_android_feature.register


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.basic_android_feature.R
import com.example.basic_android_feature.login.LoginActivity
import com.example.basic_android_feature.model.UserInfo
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private var isValidUserData: Boolean = false
    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        registrationViewModel = (activity as RegisterActivity).registrationViewModel

        btnRegister.setOnClickListener {
            if (validateUserData()) {
                val userInfo = UserInfo()
                userInfo.userName = etUserName.text.toString()
                userInfo.userJob = etJob.text.toString()
                registrationViewModel.insertUserDataViaViewModel(userInfo)
                clearTheField()
                registrationViewModel.selectUserList()
                observeUserListOfFragment()
            } else {
                Toast.makeText(activity, R.string.error_data_empty, Toast.LENGTH_LONG)
                    .show()
            }
        }

        btnClear.setOnClickListener {
            clearTheField()
        }
    }

    private fun validateUserData(): Boolean {
        isValidUserData = etUserName.text.isNotEmpty()
        isValidUserData = etJob.text.isNotEmpty()
        return isValidUserData
    }

    private fun clearTheField() {
        etUserName.text.clear()
        etJob.text.clear()
    }

    private fun observeUserListOfFragment() {
        registrationViewModel.observeUserList()
            ?.observe(viewLifecycleOwner, Observer { userInfoList ->
                if (userInfoList.isNotEmpty()) {
                    val userInfo = userInfoList.get(0)
                    Log.i("----> R ", "Name : " + userInfo.userName)
                    Log.i("----> R ", "Job : " + userInfo.userJob)
                    Log.i("----> R ", "Id : " + userInfo.userNetId)
                    Log.i("----> R ", "Created At : " + userInfo.userCreatedAt)
                    startActivity(Intent(activity, LoginActivity::class.java))
                }
            })
    }
}
