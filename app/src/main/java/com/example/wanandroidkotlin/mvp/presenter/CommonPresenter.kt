package com.example.wanandroidkotlin.mvp.presenter


import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.CommonContract
import com.example.wanandroidkotlin.utils.ss


open class CommonPresenter<M : CommonContract.Model, V : CommonContract.View>
    : BasePresenter<M, V>(), CommonContract.Presenter<V> {

    override fun addCollectArticle(id: Int) {
        mModel?.addCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCollectSuccess(true)
        }
    }

    override fun cancelCollectArticle(id: Int) {
        mModel?.cancelCollectArticle(id)?.ss(mModel, mView) {
            mView?.showCancelCollectSuccess(true)
        }
    }

}