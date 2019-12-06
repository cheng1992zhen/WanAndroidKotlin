package com.example.wanandroidkotlin.mvp.model


import com.cxz.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cxz.wanandroid.mvp.model.bean.HttpResult
import com.example.wanandroidkotlin.http.RetrofitHelper
import com.example.wanandroidkotlin.mvp.contract.ProjectListContract
import io.reactivex.Observable


class ProjectListModel : CommonModel(), ProjectListContract.Model {

    override fun requestProjectList(
        page: Int,
        cid: Int
    ): Observable<HttpResult<ArticleResponseBody>> {
        return RetrofitHelper.service.getProjectList(page, cid)
    }

}