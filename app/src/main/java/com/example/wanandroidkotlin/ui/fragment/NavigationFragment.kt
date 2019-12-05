package com.example.wanandroidkotlin.ui.fragment


import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cxz.wanandroid.mvp.model.bean.NavigationBean
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseMvpFragment
import com.example.wanandroidkotlin.mvp.contract.NavigationContract
import com.example.wanandroidkotlin.mvp.presenter.NavigationPresenter
import com.example.wanandroidkotlin.ui.adapter.NavigationAdapter
import com.example.wanandroidkotlin.ui.adapter.NavigationTabAdapter
import kotlinx.android.synthetic.main.fragment_knowledge_tree.*
import kotlinx.android.synthetic.main.fragment_knowledge_tree.multiple_status_view
import kotlinx.android.synthetic.main.fragment_knowledge_tree.recyclerView
import kotlinx.android.synthetic.main.fragment_navigation.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 *
 */
class NavigationFragment : BaseMvpFragment<NavigationContract.View, NavigationContract.Presenter>(),
    NavigationContract.View {

    private var bScroll: Boolean = false
    private var currentIndex: Int = 0
    private var bClickTab: Boolean = false

    override fun createPresenter(): NavigationContract.Presenter = NavigationPresenter()

    private val datas = mutableListOf<NavigationBean>()

    override fun attachLayoutRes(): Int = R.layout.fragment_navigation

    private val navigationAdapter: NavigationAdapter by lazy {
        NavigationAdapter(activity, datas)
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        recyclerView.run {
            layoutManager = linearLayoutManager
            itemAnimator = DefaultItemAnimator()
            adapter = navigationAdapter
            setHasFixedSize(true)
        }
        navigationAdapter.run {
            bindToRecyclerView(recyclerView)
        }

        leftRightLink()
    }

    private fun leftRightLink() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (bScroll) {
                    scrollRecyclerView()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (bScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView()
                }
                rightLinkLeft(newState)
            }

        })

        navigation_tab_layout.addOnTabSelectedListener(object :
            VerticalTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabView?, position: Int) {
            }

            override fun onTabSelected(tab: TabView?, position: Int) {
                bClickTab = true
                selectTab(position)
            }
        })
    }

    private fun selectTab(position: Int) {
        currentIndex = position
        recyclerView.stopScroll()
        smoothScrollToPosition(position)
    }

    private fun smoothScrollToPosition(position: Int) {
        val firstPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
        val lastPosition: Int = linearLayoutManager.findLastVisibleItemPosition()
        when {
            position <= firstPosition -> {
                recyclerView.smoothScrollToPosition(position)
            }
            position <= lastPosition -> {
                val top: Int = recyclerView.getChildAt(position - firstPosition).top
                recyclerView.smoothScrollBy(0, top)
            }
            else -> {
                recyclerView.smoothScrollToPosition(position)
                bScroll = true
            }
        }
    }

    private fun scrollRecyclerView() {
        bScroll = false
        val indexDistance: Int = currentIndex - linearLayoutManager.findFirstVisibleItemPosition()
        if (indexDistance > 0 && indexDistance < recyclerView!!.childCount) {
            val top: Int = recyclerView.getChildAt(indexDistance).top
            recyclerView.smoothScrollBy(0, top)
        }
    }

    private fun rightLinkLeft(newState: Any) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (bClickTab) {
                bClickTab = false
                return
            }
            val firstPosition: Int = linearLayoutManager.findFirstVisibleItemPosition()
            if (firstPosition != currentIndex) {
                currentIndex = firstPosition
                setChecked(currentIndex)
            }
        }
    }

    private fun setChecked(position: Int) {
        if (bClickTab) {
            bClickTab = false
        } else {
            navigation_tab_layout.setTabSelected(currentIndex)
        }
        currentIndex = position
    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
    }

    override fun lazyLoad() {
        mPresenter?.requestNavigationList()
    }

    override fun setNavigationData(list: List<NavigationBean>) {
        list.let {
            navigation_tab_layout.run {
                setTabAdapter(NavigationTabAdapter(activity!!.applicationContext, list))
            }
            navigationAdapter.run {
                replaceData(it)
                loadMoreComplete()
                loadMoreEnd()
                setEnableLoadMore(false)
            }
        }
        if (navigationAdapter.data.isNotEmpty()) {
            mLayoutStatusView?.showContent()
        } else {
            mLayoutStatusView?.showEmpty()
        }
    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    companion object {
        fun getInstance(): NavigationFragment {
            return NavigationFragment()
        }
    }

    fun scrollToTop() {
        navigation_tab_layout.setTabSelected(0)
    }

}
