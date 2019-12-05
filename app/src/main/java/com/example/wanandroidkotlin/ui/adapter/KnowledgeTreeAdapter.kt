package com.example.wanandroidkotlin.ui.adapter

import android.content.Context
import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cxz.wanandroid.mvp.model.bean.KnowledgeTreeBody
import com.example.wanandroidkotlin.R

class KnowledgeTreeAdapter(private val context: Context?, datas: MutableList<KnowledgeTreeBody>) :
    BaseQuickAdapter<KnowledgeTreeBody, BaseViewHolder>(R.layout.item_knowledge_tree_list, datas) {
    override fun convert(helper: BaseViewHolder?, item: KnowledgeTreeBody?) {
        helper?.setText(R.id.title_first, item?.name)
        item?.children.let {
            helper?.setText(R.id.title_second,
                it?.joinToString("    ",transform = {child ->
                    Html.fromHtml(child.name)
                })
            )
        }
    }
}