package com.example.basic_android_feature.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basic_android_feature.R
import com.example.basic_android_feature.retrofit.RetrofitService
import com.example.basic_android_feature.retrofit.UserModelRoot
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var retrofitService = RetrofitService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnGetData.setOnClickListener {
            GlobalScope.launch {
                val userModelRoot: UserModelRoot? =
                    retrofitService.userApi.getUser().execute().body()
                Log.i("-----> ", "Id : " + userModelRoot?.data?.id)
                Log.i("-----> ", "First name : " + userModelRoot?.data?.firstName)
                Log.i("-----> ", "Last name : " + userModelRoot?.data?.lastName)
                Log.i("-----> ", "Email : " + userModelRoot?.data?.email)
                Log.i("-----> ", "Avatar : " + userModelRoot?.data?.avatar)
            }
        }
    }

}
