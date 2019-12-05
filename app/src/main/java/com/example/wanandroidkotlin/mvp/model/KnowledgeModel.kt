package com.example.wanandroidkotlin.mvp.model

import com.cxz.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cxz.wanandroid.mvp.model.bean.HttpResult
import com.example.wanandroidkotlin.base.BaseModel
import com.example.wanandroidkotlin.http.RetrofitHelper
import com.example.wanandroidkotlin.mvp.contract.KnowledgeContract
import io.reactivex.Observable

class KnowledgeModel : BaseModel(), KnowledgeContract.Model {
    override fun requestKnowledgeList(
        page: Int,
        cid: Int
    ): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getKnowledgeList(page, cid)
    }

    override fun addCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.addCollectArticle(id)
    }

    override fun cancelCollectArticle(id: Int): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.cancelCollectArticle(id)
    }
}