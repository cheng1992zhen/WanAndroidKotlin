package com.example.wanandroidkotlin.rx

import com.example.wanandroidkotlin.rx.scheduler.IoMainScheduler

object SchedulerUtils {
    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}