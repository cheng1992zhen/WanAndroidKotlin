package com.example.wanandroidkotlin.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IoMainScheduler<T> :BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread()){
}