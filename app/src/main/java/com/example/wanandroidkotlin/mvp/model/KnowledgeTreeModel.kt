package com.example.wanandroidkotlin.mvp.model

import com.cxz.wanandroid.mvp.model.bean.HttpResult
import com.cxz.wanandroid.mvp.model.bean.KnowledgeTreeBody
import com.example.wanandroidkotlin.base.BaseModel
import com.example.wanandroidkotlin.http.RetrofitHelper
import com.example.wanandroidkotlin.mvp.contract.KnowledgeTreeContract
import io.reactivex.Observable

class KnowledgeTreeModel : BaseModel(), KnowledgeTreeContract.Model {
    override fun requestKnowledgeTree(): Observable<HttpResult<List<KnowledgeTreeBody>>> {
        return RetrofitHelper.service.getKnowledgeTree()
    }
}