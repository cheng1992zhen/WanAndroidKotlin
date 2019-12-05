package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.base.BasePresenter
import com.example.wanandroidkotlin.mvp.contract.ProjectContract
import com.example.wanandroidkotlin.mvp.model.ProjectModel
import com.example.wanandroidkotlin.utils.ss

class ProjectPresenter : BasePresenter<ProjectContract.Model, ProjectContract.View>(),
    ProjectContract.Presenter {
    override fun createModel(): ProjectContract.Model = ProjectModel()
    override fun requestProjectTree() {
        mModel?.requestProjectTree()?.ss(mModel, mView, false) {
            mView?.setProjectTree(it.data)
        }
    }
}