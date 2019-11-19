package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.MainContract

class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(),
    MainContract.Presenter {
    override fun logout() {

    }

    override fun getUserInfo() {

    }
}