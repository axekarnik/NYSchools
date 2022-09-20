package com.practice.nyschools.network

/**
 * Util to handle the error codes from a given range.
 * @param errorCode returned from the retrofit response
 * @return error message string depending on the error code
 */
fun errorCodesHandler(errorCode : Int) : String {
    return when (errorCode) {
        in 400..499 -> "Client Error. Bad Request, unable to fetch response"
        in 300..399 -> "Cannot handle request at this time"
        in 500..599 -> "Something wrong with the server, Please try again later."
        else -> "Generic Error"
    }


}