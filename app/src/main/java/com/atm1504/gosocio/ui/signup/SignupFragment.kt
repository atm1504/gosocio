package com.atm1504.gosocio.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.atm1504.gosocio.R
import com.atm1504.gosocio.api.LoginResponse
import com.atm1504.gosocio.api.RetrofitApi
import com.atm1504.gosocio.api.SignupResponse
import kotlinx.android.synthetic.main.fragment_signup.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_signup, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val retofitApi = RetrofitApi.create()
//        val email = RequestBody.create(MediaType.parse("text/plain"), "me@atm1504.in")
//        val password = RequestBody.create(MediaType.parse("text/plain"), "celesta2k19rockz")
//
//        val call = retofitApi.login(email, password)
//        call.enqueue(object : Callback<LoginResponse> {
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//
//                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//
//                val status = response.body()?.status()
//                if (status == 200) {
//                    Toast.makeText(context, "Successfully Logged in" + response.body()?.access_token().toString(), Toast.LENGTH_SHORT).show()
////                    findNavController().navigate(R.id.nav_login, null)
//                } else if (status == 204)
//                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
//                else if (status == 405)
//                    Toast.makeText(context, "Method not found", Toast.LENGTH_SHORT).show()
//                else if (status == 401)
//                    Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show()
//                else
//                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//        })

        signupButton.setOnClickListener {
            val name = name_input.text?.trim().toString()
            val email = email_input.text?.trim().toString()
            val password = password_input.text.toString()
            val confirm_password = confirm_password_input.text.toString()
            val aadhar_num = aadhar_input.text?.trim().toString()
            val phone = phone_input.text?.trim().toString()
            var err = 0

            if (name.isNullOrBlank() || email.isNullOrBlank() || password.isNullOrBlank() || phone?.length != 10) {
                Toast.makeText(context, "Enter proper datas int he field", Toast.LENGTH_SHORT)
                    .show()
                err += 1
            }

            if (password != confirm_password) {
                Toast.makeText(context, "EPasswords didn't match", Toast.LENGTH_SHORT).show()
                err += 1
            }

            if (aadhar_num.length != 12) {
                Toast.makeText(context, "Enter valid addhar number", Toast.LENGTH_SHORT).show()
                err += 1
            }

            if (err == 0) {
                signUpUser(name, email, phone, password, confirm_password, aadhar_num)
            }

        }
    }

    private fun signUpUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String,
        aadharNum: String
    ) {
        val retofitApi = RetrofitApi.create()
        val email = RequestBody.create(MediaType.parse("text/plain"), email)
        val name = RequestBody.create(MediaType.parse("text/plain"), name)
        val password = RequestBody.create(MediaType.parse("text/plain"), password)
        val confirm_password = RequestBody.create(MediaType.parse("text/plain"), confirmPassword)
        val phone = RequestBody.create(MediaType.parse("text/plain"), phone)
        val aadhar = RequestBody.create(MediaType.parse("text/plain"), aadharNum)

        val call = retofitApi.signup(email,password,name,phone,confirm_password,aadhar)
        call.enqueue(object : Callback<SignupResponse>{
            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<SignupResponse>,
                response: Response<SignupResponse>
            ) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}