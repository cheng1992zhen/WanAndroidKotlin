package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.ContentContract
import com.example.wanandroidkotlin.mvp.model.ContentModel

class ContentPresenter : CommonPresenter<ContentContract.Model, ContentContract.View>(),
    ContentContract.Presenter {
    override fun createModel(): ContentContract.Model = ContentModel()

}