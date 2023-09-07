package com.jbryanvega.codev.technicaltest.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.technicaltest.databinding.ItemApplicantBinding

class ApplicantViewHolder
constructor(private val binding: ItemApplicantBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(applicant: Applicant) {
        binding.applicant = applicant

        binding.executePendingBindings()
    }
}