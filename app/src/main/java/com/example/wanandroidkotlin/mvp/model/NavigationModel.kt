package com.example.wanandroidkotlin.mvp.model

import com.cxz.wanandroid.mvp.model.bean.HttpResult
import com.cxz.wanandroid.mvp.model.bean.NavigationBean
import com.example.wanandroidkotlin.base.BaseModel
import com.example.wanandroidkotlin.http.RetrofitHelper
import com.example.wanandroidkotlin.mvp.contract.NavigationContract
import io.reactivex.Observable

class NavigationModel : BaseModel(), NavigationContract.Model {
    override fun requestNavigationList(): Observable<HttpResult<List<NavigationBean>>> {
        return RetrofitHelper.service.getNavigationList()
    }
}