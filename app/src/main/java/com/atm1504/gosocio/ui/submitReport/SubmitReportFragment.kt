package com.atm1504.gosocio.ui.submitReport

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.atm1504.gosocio.R
import com.atm1504.gosocio.api.LoginResponse
import com.atm1504.gosocio.api.RetrofitApi
import com.atm1504.gosocio.api.RoadsResponse
import com.atm1504.gosocio.utils.utils
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitReportFragment : Fragment() {

    private lateinit var submitReportViewModel: SubmitReportViewModel
    private var progressDialog: ProgressDialog? = null
    val PERMISSION_ID = 42

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        submitReportViewModel =
            ViewModelProviders.of(this).get(SubmitReportViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_submit_report, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(context)
        progressDialog?.setCancelable(false)

        if(!checkPermissions()){
            requestPermissions()
        }

        getRoads()

    }

    // Function to fetch the list of roads
    private fun getRoads() {

        progressDialog?.setMessage("Getting roads...")
        progressDialog?.show()

        val retofitApi = RetrofitApi.create()

        val call = retofitApi.getRoads()
        call.enqueue(object : Callback<RoadsResponse> {
            override fun onFailure(call: Call<RoadsResponse>, t: Throwable) {
                utils.showToast(context,"Something went wrong. Please try again")
                progressDialog?.dismiss()
            }

            override fun onResponse(call: Call<RoadsResponse>, response: Response<RoadsResponse>) {
                Log.d("KHANKI","Fetched roads")
                progressDialog?.dismiss()
                loadSpinner(response.body()?.roads)

            }


        })
    }

    fun loadSpinner(roads: List<String>?) {
        utils.showToast(context,"Worked - " + roads.toString())
    }



    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSION_ID
            )
        }
    }

}