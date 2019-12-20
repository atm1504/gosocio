package com.atm1504.gosocio.ui.SubmittedReports

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.atm1504.gosocio.R

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
}