package com.practice.nyschools.usecase

import com.practice.nyschools.data.model.School
import com.practice.nyschools.network.NetworkResult
import com.practice.nyschools.repository.SchoolsRepo

class GetSchoolsListUseCase(private val repo: SchoolsRepo) {
    suspend fun getSchoolsList() : NetworkResult<List<School>?> = repo.getSchoolsList()
}