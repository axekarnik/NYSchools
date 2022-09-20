package com.practice.nyschools.network

import com.practice.nyschools.data.model.SATResults
import com.practice.nyschools.data.model.School
import retrofit2.Response
import retrofit2.http.GET

interface SchoolsApi {

    @GET("s3k6-pzi2.json")
    suspend fun getSchoolsData() : Response<List<School>>

    @GET("f9bf-2cp4.json")
    suspend fun getSATResults() : Response<List<SATResults>>
}