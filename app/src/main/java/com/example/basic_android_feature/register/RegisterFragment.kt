package com.example.basic_android_feature.register


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.basic_android_feature.R
import com.example.basic_android_feature.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private var isValidUserData: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnRegister.setOnClickListener {
            if (validateUserData()) {
                startActivity(Intent(activity, LoginActivity::class.java))
                activity?.finish()
            } else {
                Toast.makeText(activity, R.string.error_data_empty, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun validateUserData(): Boolean {
        isValidUserData = etUserName.text.isNotEmpty()
        isValidUserData = etJob.text.isNotEmpty()
        return isValidUserData
    }
}
