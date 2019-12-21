package com.atm1504.gosocio.ui.submitReport

import android.Manifest
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.atm1504.gosocio.R
import com.atm1504.gosocio.api.RetrofitApi
import com.atm1504.gosocio.api.RoadsResponse
import com.atm1504.gosocio.utils.LocationHelper
import com.atm1504.gosocio.utils.utils
import kotlinx.android.synthetic.main.fragment_report_details.*
import kotlinx.android.synthetic.main.fragment_submit_report.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubmitReportFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var submitReportViewModel: SubmitReportViewModel
    private var progressDialog: ProgressDialog? = null
    val PERMISSION_ID = 42
    lateinit var roads: List<String>
    var spinner: Spinner? = null
    var road_selected = ""
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    private lateinit var locationHelper: LocationHelper

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

        // Taking permissions
        if (!checkPermissions()) {
            requestPermissions()
        }

        // Fetching roads data
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
                utils.showToast(context, "Something went wrong. Please try again")
                progressDialog?.dismiss()
            }

            override fun onResponse(call: Call<RoadsResponse>, response: Response<RoadsResponse>) {
                Log.d("KHANKI", "Fetched roads")
                progressDialog?.dismiss()
                roads = response.body()?.roads as List<String>
                loadSpinner()

            }
        })
    }

    // Load the spinner
    fun loadSpinner() {
        utils.showToast(context, "Worked - " + roads.toString())
        spinner = this.road_spinner
        spinner?.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, roads) }
        // Set layout to use when the list of choices appear
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        road_selected = roads[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

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

    fun submitReport(){
        var complain =write_complain.text
        if(complain.length<1){
            utils.showToast(context,"Please write the complain")
        }else{
            locationHelper = LocationHelper(requireContext())
            getLocation()

        }
    }

    private fun getLocation() {
        locationHelper.getLocation()
        if (locationHelper.canGetLocation()) {
            latitude = locationHelper.latitude
            longitude = locationHelper.longitude
        }
    }

}