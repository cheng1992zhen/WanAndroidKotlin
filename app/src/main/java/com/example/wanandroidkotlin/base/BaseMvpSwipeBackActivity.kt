package com.example.wanandroidkotlin.base

import android.os.Bundle
import com.cxz.swipelibrary.SwipeBackActivityBase
import com.cxz.swipelibrary.SwipeBackActivityHelper
import com.cxz.swipelibrary.SwipeBackLayout
import com.cxz.swipelibrary.Utils
import com.cxz.wanandroid.base.IPresenter
import com.cxz.wanandroid.base.IView

abstract class BaseMvpSwipeBackActivity<in V : IView, P : IPresenter<V>> : BaseMvpActivity<V, P>(),
    SwipeBackActivityBase {
    private lateinit var mHelper: SwipeBackActivityHelper

    /**
     * SwipeBack Enable
     */
    open fun enableSwipeBack(): Boolean = true

    /**
     * 初始化 SwipeBack
     */
    private fun initSwipeBack() {
        setSwipeBackEnable(enableSwipeBack())
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHelper = SwipeBackActivityHelper(this)
        mHelper.onActivityCreate()
        initSwipeBack()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mHelper.onPostCreate()
    }

    override fun getSwipeBackLayout(): SwipeBackLayout {
        return mHelper.swipeBackLayout
    }

    override fun setSwipeBackEnable(enable: Boolean) {
        swipeBackLayout.setEnableGesture(enable)
    }

    override fun scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this)
        swipeBackLayout.scrollToFinishActivity()
    }
}