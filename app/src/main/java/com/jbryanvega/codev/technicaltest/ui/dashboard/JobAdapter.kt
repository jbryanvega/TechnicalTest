package com.jbryanvega.codev.technicaltest.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.technicaltest.databinding.ItemJobBinding
import timber.log.Timber

class JobAdapter(private val applicantItemEventListener: JobItemEventListener):
    RecyclerView.Adapter<JobViewHolder>() {

    private val jobs = ArrayList<Job>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJobBinding.inflate(inflater, parent, false)
        binding.itemClickListener = applicantItemEventListener
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(jobs[position])
    }

    override fun getItemCount(): Int {
        return jobs.size
    }

    fun clearList() {
        jobs.clear()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun add(items: List<Job>) {
        items.forEach {
            if (!jobs.contains(it)) {
                try {
                    val index = items.indexOf(it)
                    if (-1 != index) jobs.add(index, it)
                    else jobs.add(it)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }

        notifyDataSetChanged()
    }

    fun getIdOfFirstItem() : String {
        return jobs.get(0).id
    }
}