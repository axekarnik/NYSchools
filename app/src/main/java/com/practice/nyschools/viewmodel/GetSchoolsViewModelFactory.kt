package com.practice.nyschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.nyschools.repository.SchoolsRepo
import java.lang.IllegalArgumentException

class GetSchoolsViewModelFactory(val repo: SchoolsRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass) {
        GetSchoolsListViewModel::class.java -> GetSchoolsListViewModel(repo)
        GetSATResultsViewModel::class.java -> GetSATResultsViewModel(repo)
        else -> throw IllegalArgumentException("ViewModel class not defined")
    } as T

}