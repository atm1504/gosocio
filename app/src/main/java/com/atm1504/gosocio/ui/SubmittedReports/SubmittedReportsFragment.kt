package com.atm1504.gosocio.ui.SubmittedReports

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.atm1504.gosocio.R
import com.atm1504.gosocio.api.ReportedTasksResponse
import com.atm1504.gosocio.api.RetrofitApi
import com.atm1504.gosocio.api.SignupResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response

class SubmittedReportsFragment : Fragment() {

    private lateinit var submittedReportsViewModel: SubmittedReportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        submittedReportsViewModel =
            ViewModelProviders.of(this).get(SubmittedReportsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_submitted_reports, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchReports()

    }

    fun fetchReports() {
        val dummy_access_token = "retyuecu7fb76b46745376453"
        val dummy_email = "reuuye@example.com"

        val retrofitApi = RetrofitApi.create()
        val access_token = RequestBody.create(MediaType.parse("text/plain"), dummy_access_token)
        val email = RequestBody.create(MediaType.parse("text/plain"), dummy_email)

        val call = retrofitApi.getReportedTasks(email, access_token)

        call.enqueue(object : retrofit2.Callback<ReportedTasksResponse> {
            override fun onFailure(call: retrofit2.Call<ReportedTasksResponse>, t: Throwable) {
                Log.d("KHANKI","Failed")
            }

            override fun onResponse(
                call: retrofit2.Call<ReportedTasksResponse>,
                response: Response<ReportedTasksResponse>
            ) {
                Toast.makeText(context,"fetched",Toast.LENGTH_LONG).show()
                Log.d("KHANKI", response.body()?.toString())
            }

        })

    }
}