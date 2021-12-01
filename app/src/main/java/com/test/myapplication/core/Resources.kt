package com.test.myapplication.core

import java.lang.Exception

sealed class Resources<out T> {

    //declaramos estados
    class Loading<out T>: Resources<T>()
    data class Success<out T>(val data: T): Resources<T>()
    data class Failure(val exception: Exception): Resources<Nothing>()

}