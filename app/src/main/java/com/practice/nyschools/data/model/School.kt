package com.practice.nyschools.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class School(
@SerializedName("dbn")
val id: String,
@SerializedName("school_email")
val email: String?,
@SerializedName("website")
val website: String?,
@SerializedName("phone_number")
val phoneNumber: String?,
@SerializedName("school_name")
val name: String,
@SerializedName("location")
val address: String?
) : Parcelable

data class SATResults(
@SerializedName("dbn")
val id: String,
@SerializedName("school_name")
val name: String,
@SerializedName("num_of_sat_test_takers")
val candidateCount: String,
@SerializedName("sat_critical_reading_avg_score")
val readingAvgScore: String,
@SerializedName("sat_math_avg_score")
val mathAvgScore: String,
@SerializedName("sat_writing_avg_score")
val writingAvgScore: String
)