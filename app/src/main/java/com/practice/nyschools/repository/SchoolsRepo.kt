package com.practice.nyschools.repository

import com.practice.nyschools.data.model.SATResults
import com.practice.nyschools.data.model.School
import com.practice.nyschools.network.NetworkResult
import com.practice.nyschools.network.RetrofitClient
import com.practice.nyschools.network.SchoolsApi
import com.practice.nyschools.network.errorCodesHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SchoolsRepo {
    private val api = RetrofitClient.getInstance().create(SchoolsApi::class.java)

    /**
     * @param None
     * @return NetworkResult<List<School>> for a list of Schools
     */
    suspend fun getSchoolsList() : NetworkResult<List<School>?> =
       withContext(Dispatchers.IO) {
           try {
               val response = api.getSchoolsData()
               if (response.isSuccessful) {
                   response.body().let {
                       NetworkResult.Success(response.body())
                   }
               } else {
                   val errorString = errorCodesHandler(response.code())
                   NetworkResult.Error(errorString)
               }
           } catch (e : Exception) {
               NetworkResult.Error("Error in fetching the Schools data")
           }
       }

    /**
     * @param None
     * @return NetworkResult<List<SATResults>> for a list of schools
     */
    suspend fun getSATResults() : NetworkResult<List<SATResults>?> =
        try{
            withContext(Dispatchers.IO) {
                val response = api.getSATResults()
                if (response.isSuccessful) {
                    response.body().let {
                        NetworkResult.Success(it)
                    }
                } else {
                    val errorString = errorCodesHandler(response.code())
                    NetworkResult.Error(errorString)
                }
            }
        } catch (e : Exception) {
            NetworkResult.Error("Error in fetching the SAT Results data")
        }
}