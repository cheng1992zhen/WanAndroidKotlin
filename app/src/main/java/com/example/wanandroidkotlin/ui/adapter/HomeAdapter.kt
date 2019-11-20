package com.example.wanandroidkotlin.ui.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cxz.wanandroid.mvp.model.bean.Article
import com.example.wanandroidkotlin.R

class HomeAdapter(private val context: Context?, datas: MutableList<Article>)  : BaseQuickAdapter<Article, BaseViewHolder>(
    R.layout.item_home_list, datas) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {

    }
}