package com.example.basic_android_feature.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.basic_android_feature.R
import com.example.basic_android_feature.model.UserInfo
import com.example.basic_android_feature.retrofit.RetrofitService
import com.example.basic_android_feature.retrofit.UserModelRoot
import com.example.basic_android_feature.room.UserDao
import com.example.basic_android_feature.room.UserRoomDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private var isFilledAllInputs: Boolean = false
    private lateinit var userDao: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userDao = UserRoomDatabase.getDatabase(activity!!.applicationContext).userDao()

        btnSave.setOnClickListener {

            val name: String = etName.text.toString()
            val age: String = etAge.text.toString()
            val place: String = etLocation.text.toString()

            if (validateInputs(name, age, place)) {
                val userInfo = UserInfo()
                userInfo.userName = name
                userInfo.userAge = age
                userInfo.userPlace = place
                GlobalScope.launch {
                    insertIntoRoomDataBase(userInfo)
                }
                clearData()
            }
        }

        btnRetrieve.setOnClickListener {
            GlobalScope.launch {
                selectAllUserInfo()
            }
        }

        btnGetData.setOnClickListener {
            getDataFromNetwork()
        }
    }

    fun validateInputs(name: String, age: String, location: String): Boolean {

        isFilledAllInputs = name.isNotEmpty()
        isFilledAllInputs = age.isNotEmpty()
        isFilledAllInputs = location.isNotEmpty()

        return isFilledAllInputs

    }

    private fun insertIntoRoomDataBase(userInfo: UserInfo) {
        userDao.insertUser(userInfo)
    }

    private fun clearData() {
        etName.text.clear()
        etAge.text.clear()
        etLocation.text.clear()
    }

    private fun selectAllUserInfo() {
        val userList: List<UserInfo> = userDao.selectAllUser()

        for (user in userList) {
            Log.i("-----------", "-----------------")
            Log.i("--->", "UserId : " + user.uid)
            Log.i("---> ", "Name : " + user.userName)
            Log.i("---> ", "Age : " + user.userAge)
            Log.i("---> ", "Place : " + user.userPlace)
        }
    }

    private fun getDataFromNetwork() {
        val retrofitService = RetrofitService()
        GlobalScope.launch {
            retrofitService.userApi.getUser().enqueue(object : Callback<UserModelRoot> {
                override fun onFailure(call: Call<UserModelRoot>, t: Throwable) {
                    Log.i("----> ", "Error : " + t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<UserModelRoot>,
                    response: Response<UserModelRoot>
                ) {
                    var userModelRoot: UserModelRoot? = response.body()
                    Log.i("--->", "User Id : " + userModelRoot?.data?.id)
                    Log.i("--->", "First name : " + userModelRoot?.data?.firstName)
                    Log.i("--->", "Last name : " + userModelRoot?.data?.lastName)
                    Log.i("--->", "Email  : " + userModelRoot?.data?.email)
                    Log.i("--->", "Avator  : " + userModelRoot?.data?.avatar)
                }
            })
        }
    }

}
