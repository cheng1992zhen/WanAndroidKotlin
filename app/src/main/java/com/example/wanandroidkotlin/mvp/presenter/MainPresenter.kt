package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.MainContract
import com.example.wanandroidkotlin.mvp.model.MainModel
import com.example.wanandroidkotlin.utils.ss
import com.example.wanandroidkotlin.utils.sss

class MainPresenter : BasePresenter<MainContract.Model, MainContract.View>(),
    MainContract.Presenter {

    override fun createModel(): MainContract.Model = MainModel()
    override fun logout() {
        mModel?.logout()?.ss(mModel, mView) {
            mView?.showLogoutSuccess(success = true)
        }
    }

    override fun getUserInfo() {
        mModel?.getUserInfo()?.sss(mView, false, {
            mView?.showUserInfo(it.data)
        }, {})
    }
}