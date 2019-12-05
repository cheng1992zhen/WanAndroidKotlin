package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.KnowledgeTreeContract
import com.example.wanandroidkotlin.mvp.model.KnowledgeTreeModel
import com.example.wanandroidkotlin.utils.ss

class KnowledgeTreePresenter :
    BasePresenter<KnowledgeTreeContract.Model, KnowledgeTreeContract.View>(),
    KnowledgeTreeContract.Presenter {
    override fun createModel(): KnowledgeTreeContract.Model? = KnowledgeTreeModel()
    override fun requestKnowledgeTree() {
        mModel?.requestKnowledgeTree()?.ss(mModel, mView, false) {
            mView?.setKnowledgeTree(it.data)
        }
    }

}