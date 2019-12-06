package com.example.wanandroidkotlin.mvp.presenter

import com.example.wanandroidkotlin.mvp.contract.ProjectListContract
import com.example.wanandroidkotlin.mvp.model.ProjectListModel
import com.example.wanandroidkotlin.utils.ss


class ProjectListPresenter : CommonPresenter<ProjectListContract.Model, ProjectListContract.View>(),
    ProjectListContract.Presenter {

    override fun createModel(): ProjectListContract.Model? = ProjectListModel()

    override fun requestProjectList(page: Int, cid: Int) {
        mModel?.requestProjectList(page, cid)?.ss(mModel, mView, page == 1) {
            mView?.setProjectList(it.data)
        }
    }

}