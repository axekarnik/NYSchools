package com.practice.nyschools.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.nyschools.R
import com.practice.nyschools.adapter.SchoolListAdapter
import com.practice.nyschools.data.model.School
import com.practice.nyschools.databinding.FragmentSchoolListBinding
import com.practice.nyschools.repository.SchoolsRepo
import com.practice.nyschools.viewmodel.GetSchoolsListViewModel
import com.practice.nyschools.viewmodel.GetSchoolsViewModelFactory

class SchoolListFragment : Fragment() {
    lateinit var binding : FragmentSchoolListBinding

    private val viewModelProvider by lazy {
        GetSchoolsViewModelFactory(SchoolsRepo())
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelProvider)[GetSchoolsListViewModel::class.java]
    }

    private val mAdapter by lazy {
        SchoolListAdapter { school -> setItemClickListener(school) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.schoolList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            hasFixedSize()
        }
        getSchoolData()
        viewModel.schoolListLiveData.observe(viewLifecycleOwner, schoolDataObserver)
        viewModel.schoolListErrorLiveData.observe(viewLifecycleOwner,errorStateObserver)
        binding.buttonRetry.setOnClickListener {
            getSchoolData()
        }
    }

    private fun getSchoolData() {
        binding.progressBar.visibility = View.VISIBLE
        handleErrorViews(false)
        viewModel.getSchoolsList()
    }

    private val schoolDataObserver = Observer<List<School>?> {schools->
        binding.progressBar.visibility = View.GONE
        mAdapter.setData(schools)
    }

    private val errorStateObserver = Observer<String?> {errorMessage ->
        binding.progressBar.visibility = View.GONE
        binding.errorText.text = errorMessage
        if(mAdapter.schoolList.isEmpty())
            handleErrorViews(true)
    }

    private fun handleErrorViews(isError : Boolean){
        binding.apply {
            errorText.visibility = if(isError)  View.VISIBLE else View.GONE
            buttonRetry.visibility = if(isError)  View.VISIBLE else View.GONE
        }
    }

    private fun setItemClickListener(school: School) {
        val bundle = bundleOf("School" to school)
        findNavController().navigate(R.id.action_fragmentSchoolList_to_schoolDetailsFragment, bundle)
    }
}