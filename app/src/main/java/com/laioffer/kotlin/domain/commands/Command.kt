package com.laioffer.kotlin.domain.commands

interface Command<out T> {
    suspend fun execute(): T
}
