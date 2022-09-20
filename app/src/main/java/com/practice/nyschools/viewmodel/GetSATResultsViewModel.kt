package com.practice.nyschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.nyschools.data.model.SATResults
import com.practice.nyschools.network.NetworkResult
import com.practice.nyschools.repository.SchoolsRepo
import com.practice.nyschools.usecase.GetSATResultsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetSATResultsViewModel(val repo: SchoolsRepo) : ViewModel() {
    private val satResultsMutableLiveData = MutableLiveData<List<SATResults>?>()
    val satResultsListLiveData: LiveData<List<SATResults>?>  get() = satResultsMutableLiveData

    private val satResultsErrorMutableLiveData = MutableLiveData<String?>()
    val satResultsErrorLiveData: LiveData<String?> get() = satResultsErrorMutableLiveData

    fun getSATResults(id: String) {
        viewModelScope.launch(Dispatchers.Main) {
            when(val result = GetSATResultsUseCase(repo).getSATResults()) {
                is NetworkResult.Success -> {
                    processSuccessData(id, result.data)
                }
                is NetworkResult.Error -> satResultsErrorMutableLiveData.value = result.message
            }
        }
    }

    private fun processSuccessData(id: String, data: List<SATResults>?) {
        val resultData = data?.filter { results -> results.id == id }
        satResultsMutableLiveData.value = resultData
    }
}