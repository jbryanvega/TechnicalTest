package com.jbryanvega.codev.technicaltest.ui.dashboard

import androidx.recyclerview.widget.RecyclerView
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.technicaltest.databinding.ItemJobBinding

class JobViewHolder
constructor(private val binding: ItemJobBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(job: Job) {
        binding.job = job

        binding.executePendingBindings()
    }
}