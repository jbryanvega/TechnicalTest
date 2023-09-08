package com.jbryanvega.codev.technicaltest.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.technicaltest.R
import com.jbryanvega.codev.technicaltest.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : Fragment(), JobItemEventListener {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var jobAdapter: JobAdapter

    private val viewModel: DashboardViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.jobAdd.setOnClickListener { viewModel.addRandomJob() }
        binding.jobSearch.setOnClickListener { searchJob() }
        binding.jobClear.setOnClickListener { clearJob() }
        binding.jobGetFirst.setOnClickListener {
            with(jobAdapter) {
                viewModel.displayFirstJob(getIdOfFirstItem())
            }
        }

        viewModel.job.observe(viewLifecycleOwner) {
            binding.jobOnly.text = String.format("${it.id}\n${it.noOfOpenings}\n" +
                    "${it.title}\n" +
                    "${it.description}\n" +
                    "${it.industry}")
        }
        viewModel.jobs.observe(viewLifecycleOwner) {
            onUpdateJobs(it)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onUpdateJobs(jobs: List<Job>) {
        with(jobAdapter) {
            clearList()
            add(jobs)
        }
    }

    private fun setupRecycleView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), linearLayoutManager.orientation).apply {
                setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
            }

        jobAdapter = JobAdapter(this)

        with(binding.jobList) {
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = jobAdapter
        }
    }

    override fun onJobDelete(id: String) {
        viewModel.deleteJob(id)
    }

    override fun onJobUpdate(id: String) {
        viewModel.updateJob(id)
    }

    private fun searchJob() {
        val keyword = binding.jobKeyword.text.toString()
        val industry = binding.jobIndustrySpinner.selectedItemId.toInt()

        Timber.d(keyword)
        Timber.d("$industry")

        viewModel.searchJob(keyword, industry)
    }

    private fun clearJob() {
        viewModel.clearJobSearch()
    }
}