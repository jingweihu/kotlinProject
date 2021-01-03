package com.laioffer.kotlin

import java.net.URL

class Request(val url: String) {

    suspend fun run(): String {
        return URL(url).readText()
    }
}