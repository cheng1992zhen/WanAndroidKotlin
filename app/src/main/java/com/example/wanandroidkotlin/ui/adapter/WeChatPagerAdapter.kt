package com.example.wanandroidkotlin.ui.adapter


import android.text.Html
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.cxz.wanandroid.mvp.model.bean.WXChapterBean
import com.example.wanandroidkotlin.ui.fragment.KnowledgeFragment


class WeChatPagerAdapter(private val list: MutableList<WXChapterBean>, fm: FragmentManager?) :
    FragmentStatePagerAdapter(fm!!) {

    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(KnowledgeFragment.getInstance(it.id))
        }
    }

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? = Html.fromHtml(list[position].name)

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE


}