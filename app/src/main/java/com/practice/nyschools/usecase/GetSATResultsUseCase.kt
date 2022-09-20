package com.practice.nyschools.usecase

import com.practice.nyschools.data.model.SATResults
import com.practice.nyschools.network.NetworkResult
import com.practice.nyschools.repository.SchoolsRepo

class GetSATResultsUseCase(private val repo: SchoolsRepo) {
    suspend fun getSATResults() : NetworkResult<List<SATResults>?> = repo.getSATResults()
}