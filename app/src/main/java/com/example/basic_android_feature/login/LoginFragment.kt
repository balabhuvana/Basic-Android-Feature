package com.example.basic_android_feature.login


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.basic_android_feature.R
import com.example.basic_android_feature.home.HomeActivity
import com.example.basic_android_feature.model.UserInfo
import com.example.basic_android_feature.util.ConstantUtil
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var isValidUserData: Boolean = false
    private lateinit var userInfo: UserInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = (activity as LoginActivity).loginViewModel

        loginViewModel.selectUserList()

        observeUserListOfFragment()

        btnLogin.setOnClickListener {
            if (validateUserData()) {
                userInfo.isUserLogined = true
                loginViewModel.updateUserLoginStatus(userInfo)
                loginViewModel.selectSpecificUser(userInfo.uid)
                observerSpecificUser()
            }
        }
    }

    private fun observeUserListOfFragment() {
        loginViewModel.observeUserList()
            ?.observe(viewLifecycleOwner, Observer { userInfoList ->
                if (userInfoList.isNotEmpty()) {
                    userInfo = userInfoList.get(0)
                    etUserName.setText(userInfo.userName)
                    etJob.setText(userInfo.userName)
                }
            })
    }

    private fun validateUserData(): Boolean {
        isValidUserData = etUserName.text.isNotEmpty()
        isValidUserData = etJob.text.isNotEmpty()
        return isValidUserData
    }

    private fun observerSpecificUser() {
        loginViewModel.observeUserInfo().observe(viewLifecycleOwner, Observer { userInfo ->
            if (userInfo.isUserLogined) {
                userId = userInfo.uid
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        })
    }

    companion object {
        var userId: Int? = null
    }

}
