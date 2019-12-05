package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.NavigationContract
import com.example.wanandroidkotlin.mvp.model.NavigationModel
import com.example.wanandroidkotlin.utils.ss

class NavigationPresenter : BasePresenter<NavigationContract.Model, NavigationContract.View>(),
    NavigationContract.Presenter {
    override fun createModel(): NavigationContract.Model = NavigationModel()
    override fun requestNavigationList() {
        mModel?.requestNavigationList()?.ss(mModel, mView, false) {
            mView?.setNavigationData(it.data)
        }
    }
}