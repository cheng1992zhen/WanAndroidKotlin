package com.example.wanandroidkotlin.mvp.model

import com.cxz.wanandroid.mvp.model.bean.HttpResult
import com.cxz.wanandroid.mvp.model.bean.UserInfoBody
import com.example.wanandroidkotlin.base.BaseModel
import com.example.wanandroidkotlin.http.RetrofitHelper
import com.example.wanandroidkotlin.mvp.contract.MainContract
import io.reactivex.Observable

class MainModel : BaseModel(), MainContract.Model {

    override fun logout(): Observable<HttpResult<Any>> {
        return RetrofitHelper.service.logout()
    }

    override fun getUserInfo(): Observable<HttpResult<UserInfoBody>> {
        return RetrofitHelper.service.getUserInfo()
    }
}