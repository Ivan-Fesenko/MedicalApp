package com.example.medicalapp.basic.concurrency

interface TaskCallback<T> {
    fun onComplete(result: T)
    fun onError(fault: Fault)
}
