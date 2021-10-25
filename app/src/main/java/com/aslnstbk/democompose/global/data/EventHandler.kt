package com.aslnstbk.democompose.global.data

interface EventHandler<T> {

    fun obtainEvent(event: T)
}