package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.HomeContract

class HomePresenter :BasePresenter<HomeContract.Model,HomeContract.View>(),HomeContract.Presenter {
    override fun requestBanner() {

    }

    override fun requestHomeData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestArticles(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addCollectArticle(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelCollectArticle(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}