package com.atm1504.gosocio.api

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RetrofitApi {

    @Multipart
    @POST("login.php")
    fun login(@Part("email") email: RequestBody, @Part("password") password: RequestBody): Call<LoginResponse>

    @Multipart
    @POST("signup.php")
    fun signup(
        @Part("email") email: RequestBody, @Part("password") password: RequestBody, @Part("name") name: RequestBody, @Part(
            "phone"
        ) phone: RequestBody, @Part("confirm_password") confirm_password: RequestBody, @Part("aadfhar") aadhar: RequestBody
    ): Call<SignupResponse>


    companion object Factory {
        fun create(): RetrofitApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://celesta.org.in/backend/admin/functions/app/")
                .build()

            return retrofit.create(RetrofitApi::class.java)
        }
    }
}