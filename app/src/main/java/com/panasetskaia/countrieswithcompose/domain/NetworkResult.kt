package com.panasetskaia.countrieswithcompose.domain

data class NetworkResult(val status: Status, val msg: String?) {

    companion object {
        fun loading(): NetworkResult {
            return NetworkResult(Status.LOADING,  null)
        }
        fun success(): NetworkResult {
            return NetworkResult(Status.SUCCESS,  null)
        }
        fun error(msg: String?): NetworkResult {
            return NetworkResult(Status.ERROR, msg)
        }
    }
}
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}