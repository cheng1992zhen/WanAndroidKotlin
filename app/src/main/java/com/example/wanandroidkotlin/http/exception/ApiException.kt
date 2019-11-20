package com.example.wanandroidkotlin.http.exception

import java.lang.RuntimeException

class ApiException : RuntimeException {
    private var code: Int? = null

    constructor(message: String) : super(message)

    constructor(throwable: Throwable, code: Int):super(throwable){
        this.code = code
    }
}