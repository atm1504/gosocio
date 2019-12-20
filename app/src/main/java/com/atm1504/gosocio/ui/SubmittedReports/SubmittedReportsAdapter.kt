package com.atm1504.gosocio.ui.SubmittedReports

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atm1504.gosocio.R
import com.atm1504.gosocio.api.Report

class SubmittedReportsAdapter : RecyclerView.Adapter<SubmittedReportsAdapter.ViewHolder>() {
    private var reportsList: List<Report> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubmittedReportsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.reports_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reportsList.size
    }

    fun setSumittedReportsList(list: List<Report>) {
        this.reportsList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SubmittedReportsAdapter.ViewHolder, position: Int) {
        holder.coins.text = reportsList.get(position).coins.toString()
        holder.road_name.text = reportsList.get(position).road_name
        holder.current_status.text = reportsList.get(position).current_status
        holder.complain.text = reportsList.get(position).complain
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val road_name: TextView = itemView.findViewById(R.id.road_name)
        val current_status: TextView = itemView.findViewById(R.id.current_status)
        val complain: TextView = itemView.findViewById(R.id.complain)
        val coins: TextView = itemView.findViewById(R.id.coins)
    }
}