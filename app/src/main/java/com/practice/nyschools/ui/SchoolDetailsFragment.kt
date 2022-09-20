package com.practice.nyschools.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practice.nyschools.R
import com.practice.nyschools.data.model.SATResults
import com.practice.nyschools.data.model.School
import com.practice.nyschools.databinding.FragmentSchoolDetailsBinding
import com.practice.nyschools.repository.SchoolsRepo
import com.practice.nyschools.viewmodel.GetSATResultsViewModel
import com.practice.nyschools.viewmodel.GetSchoolsListViewModel
import com.practice.nyschools.viewmodel.GetSchoolsViewModelFactory

class SchoolDetailsFragment : Fragment() {
    lateinit var binding: FragmentSchoolDetailsBinding

    private val viewModelProvider by lazy {
        GetSchoolsViewModelFactory(SchoolsRepo())
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[GetSATResultsViewModel::class.java]
    }

    private val schoolData by lazy {
        arguments?.get("School") as School
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadData()
        viewModel.satResultsListLiveData.observe(viewLifecycleOwner, resultsObserver)
        viewModel.satResultsErrorLiveData.observe(viewLifecycleOwner, errorStateObserver)
    }

    private fun initViews() {
        binding.detailsSchoolName.text = schoolData.name
        binding.emailValue.text  = if(schoolData.email != null) schoolData.email else "N/A"
        binding.phoneValue.text = if(schoolData.phoneNumber != null) schoolData.phoneNumber else "N/A"
        binding.websiteValue.text = if (schoolData.website != null) schoolData.website else "N/A"
        binding.addressValue.text = if(schoolData.address != null) formatAddressString(schoolData.address!!) else "N/A"
    }

    private fun loadData() {
       binding.progressBarDetails.visibility = View.VISIBLE
       viewModel.getSATResults(schoolData?.id)
    }

    private val resultsObserver = Observer<List<SATResults>?> {
        binding.progressBarDetails.visibility = View.GONE
        if(it.isNotEmpty()) {
            binding.mathScoreValue.text = it[0].mathAvgScore
            binding.readingScoreValue.text =  it[0].readingAvgScore
            binding.writingScoreValue.text =  it[0].writingAvgScore
        }
    }

    private val errorStateObserver = Observer<String?> {
        binding.progressBarDetails.visibility = View.GONE
    }

    private fun formatAddressString(address: String) =
        address?.apply {
           return substring(0, indexOf('('))
        }
}