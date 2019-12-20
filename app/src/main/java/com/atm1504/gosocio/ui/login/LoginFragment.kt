package com.atm1504.gosocio.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.atm1504.gosocio.R
import com.atm1504.gosocio.api.LoginResponse
import com.atm1504.gosocio.api.RetrofitApi
import com.atm1504.gosocio.api.SignupResponse
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener {
            val email = email_input.text?.trim().toString()
            val password = password_input.text?.trim().toString()
            val err =0

            if(email.isNullOrBlank() || password.isNullOrBlank()){
                Toast.makeText(context,"Enter allt he fields correctly",Toast.LENGTH_LONG).show()
            }else{
                loginUser(email,password)
            }

        }
    }

    private fun loginUser(email: String, password: String) {
        val retofitApi = RetrofitApi.create()
        val email = RequestBody.create(MediaType.parse("text/plain"), email)
        val password = RequestBody.create(MediaType.parse("text/plain"), password)

        val call = retofitApi.login(email,password)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }


}