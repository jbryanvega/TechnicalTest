package com.jbryanvega.codev.technicaltest.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.technicaltest.databinding.ItemApplicantBinding
import timber.log.Timber

class ApplicantAdapter(private val applicantItemEventListener: ApplicantItemEventListener):
    RecyclerView.Adapter<ApplicantViewHolder>() {

    private val applicants = ArrayList<Applicant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemApplicantBinding.inflate(inflater, parent, false)
        binding.itemClickListener = applicantItemEventListener
        return ApplicantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApplicantViewHolder, position: Int) {
        holder.bind(applicants[position])
    }

    override fun getItemCount(): Int {
        return applicants.size
    }

    fun clearList() {
        applicants.clear()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun add(items: List<Applicant>) {
        items.forEach {
            if (!applicants.contains(it)) {
                try {
                    val index = items.indexOf(it)
                    if (-1 != index) applicants.add(index, it)
                    else applicants.add(it)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        }

        notifyDataSetChanged()
    }

    fun getIdOfFirstItem() : String {
        return applicants.get(0).id
    }
}