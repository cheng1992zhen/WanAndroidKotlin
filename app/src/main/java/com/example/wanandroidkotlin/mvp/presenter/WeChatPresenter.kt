package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.WeChatContract
import com.example.wanandroidkotlin.mvp.model.WeChatModel
import com.example.wanandroidkotlin.utils.ss

class WeChatPresenter : BasePresenter<WeChatContract.Model, WeChatContract.View>(),
    WeChatContract.Presenter {
    override fun createModel(): WeChatContract.Model = WeChatModel()
    override fun getWXChapters() {
        mModel?.getWXChapters()?.ss(mModel, mView, false) {
            mView?.showWXChapters(it.data)
        }
    }
}