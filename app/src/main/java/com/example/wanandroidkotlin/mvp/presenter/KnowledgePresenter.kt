package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.KnowledgeContract
import com.example.wanandroidkotlin.mvp.model.KnowledgeModel
import com.example.wanandroidkotlin.utils.ss

class KnowledgePresenter : BasePresenter<KnowledgeContract.Model, KnowledgeContract.View>(),
    KnowledgeContract.Presenter {
    override fun createModel(): KnowledgeContract.Model = KnowledgeModel()
    override fun requestKnowledgeList(page: Int, cid: Int) {
        mModel?.requestKnowledgeList(page, cid)?.ss(mModel, mView, false) {
            mView?.setKnowledgeList(it.data)
        }
    }

    override fun addCollectArticle(id: Int) {

    }

    override fun cancelCollectArticle(id: Int) {

    }
}