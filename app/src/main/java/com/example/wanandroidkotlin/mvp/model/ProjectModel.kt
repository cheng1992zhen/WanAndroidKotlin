package com.example.wanandroidkotlin.mvp.model

import com.cxz.wanandroid.mvp.model.bean.HttpResult
import com.cxz.wanandroid.mvp.model.bean.ProjectTreeBean
import com.example.wanandroidkotlin.base.BaseModel
import com.example.wanandroidkotlin.http.RetrofitHelper
import com.example.wanandroidkotlin.mvp.contract.ProjectContract
import io.reactivex.Observable

class ProjectModel : BaseModel(), ProjectContract.Model {
    override fun requestProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>> {
        return RetrofitHelper.service.getProjectTree()
    }
}