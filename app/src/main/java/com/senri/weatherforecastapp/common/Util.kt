package com.senri.weatherforecastapp.common

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

fun resolveException(e: Exception): String {
    val message = "Something went wrong.\nPlease try again later."
    when (e) {
        is SocketTimeoutException -> {
            return "Timeout error: The request took too long to complete. Please try again later."
        }

        is ConnectException -> {
            return "No internet, please check your internet connection and retry…"
        }

        is SocketException -> {
            return "Service is currently unavailable, please try again later"
        }

        is UnknownHostException -> {
            return "No internet, please check your internet connection and retry…"
        }

        is SSLHandshakeException -> {
            return "Insecure connection detected. Please try again later"
        }

    }

    return message
}