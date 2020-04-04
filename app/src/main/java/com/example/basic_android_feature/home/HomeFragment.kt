package com.example.basic_android_feature.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.basic_android_feature.R
import com.example.basic_android_feature.model.UserInfo
import com.example.basic_android_feature.setting.SettingFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel = (activity as HomeActivity).homeViewModel
        homeViewModel.selectUserList()

        observeUserListOfFragment()

        btnSetting.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.myHomeContainer, SettingFragment()).commit()
        }
    }

    private fun observeUserListOfFragment() {
        homeViewModel.observeUserList()
            ?.observe(viewLifecycleOwner, Observer { userInfoList ->
                if (userInfoList.isNotEmpty()) {
                    val userInfo = userInfoList[0]
                    userInfoValue = userInfo
                    tvUserNameValue.text = userInfo.userName
                    tvJobValue.text = userInfo.userJob
                    tvUserIdValue.text = userInfo.userNetId
                    tvCreatedAtValue.text = userInfo.userCreatedAt
                }
            })
    }

    companion object {
        var userInfoValue: UserInfo? = null
    }

}
