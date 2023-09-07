package com.jbryanvega.codev.technicaltest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.lib.network.ApiSuccessResponse
import com.jbryanvega.codev.technicaltest.R
import com.jbryanvega.codev.technicaltest.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), ApplicantItemEventListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var applicantAdapter: ApplicantAdapter

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.applicantAdd.setOnClickListener { viewModel.addRandomApplicant() }
        binding.applicantGetFirst.setOnClickListener {
            with(applicantAdapter) {
                viewModel.displayFirstApplicant(getIdOfFirstItem())
            }
        }

        viewModel.applicant.observe(viewLifecycleOwner) {
            binding.applicantOnly.text = String.format("${it.id}\n${it.fullName}\n${it.emailAddress}")
        }
        viewModel.applicants.observe(viewLifecycleOwner) {
            onUpdateApplicants(it)
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

    private fun onUpdateApplicants(applicants: List<Applicant>) {
        with(applicantAdapter) {
            clearList()
            add(applicants)
        }
    }

    private fun setupRecycleView() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), linearLayoutManager.orientation).apply {
                setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
            }

        applicantAdapter = ApplicantAdapter(this)

        with(binding.applicantList) {
            layoutManager = linearLayoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = applicantAdapter
        }
    }

    override fun onApplicantDelete(id: String) {
        viewModel.deleteApplicant(id)
    }

    override fun onApplicantUpdate(id: String) {
        viewModel.updateApplicant(id)
    }


}