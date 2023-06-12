package com.panasetskaia.countrieswithcompose.domain

data class NetworkResult<out T>(val status: Status, val data: T?, val msg: String?) {

    companion object {
        fun <T> success(data: T): NetworkResult<T> {
            return NetworkResult(Status.SUCCESS, data, null)
        }
        fun <T> error(data: T?, msg: String?): NetworkResult<T> {
            return NetworkResult(Status.ERROR, data, msg)
        }
    }
}
enum class Status {
    SUCCESS,
    ERROR
}