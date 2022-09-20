package com.practice.nyschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.nyschools.data.model.School
import com.practice.nyschools.network.NetworkResult
import com.practice.nyschools.repository.SchoolsRepo
import com.practice.nyschools.usecase.GetSchoolsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetSchoolsListViewModel(val repo: SchoolsRepo) : ViewModel() {
    private val schoolListMutableLiveData = MutableLiveData<List<School>?>()
    val schoolListLiveData : LiveData<List<School>?> get() = schoolListMutableLiveData

    private val schoolListErrorMutableLiveData = MutableLiveData<String?>()
    val schoolListErrorLiveData : LiveData<String?> get() = schoolListErrorMutableLiveData

     fun getSchoolsList() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = GetSchoolsListUseCase(repo).getSchoolsList()
            when(result) {
                is NetworkResult.Success -> {
                    schoolListMutableLiveData.value = result.data
                }
                is NetworkResult.Error -> schoolListErrorMutableLiveData.value = result.message
            }
        }
    }

}