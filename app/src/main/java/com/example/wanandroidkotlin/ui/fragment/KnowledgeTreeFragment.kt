package com.example.wanandroidkotlin.ui.fragment


import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cxz.wanandroid.constant.Constant
import com.cxz.wanandroid.mvp.model.bean.KnowledgeTreeBody
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseMvpFragment
import com.example.wanandroidkotlin.mvp.contract.KnowledgeTreeContract
import com.example.wanandroidkotlin.mvp.presenter.KnowledgeTreePresenter
import com.example.wanandroidkotlin.ui.activity.KnowledgeActivity
import com.example.wanandroidkotlin.ui.adapter.KnowledgeTreeAdapter
import com.example.wanandroidkotlin.utils.showToast
import com.example.wanandroidkotlin.widget.RecyclerViewItemDecoration
import kotlinx.android.synthetic.main.fragment_knowledge_tree.*

/**
 * A simple [Fragment] subclass.
 */
class KnowledgeTreeFragment :
    BaseMvpFragment<KnowledgeTreeContract.View, KnowledgeTreeContract.Presenter>(),
    KnowledgeTreeContract.View {

    /**
     * datas
     */
    private val datas = mutableListOf<KnowledgeTreeBody>()
    /**
     * Adapter
     */
    private val knowledgeTreeAdapter: KnowledgeTreeAdapter by lazy {
        KnowledgeTreeAdapter(activity, datas)
    }
    private val recyclerViewItemDecoration by lazy {
        activity?.let { RecyclerViewItemDecoration(it, LinearLayoutManager.VERTICAL) }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun createPresenter(): KnowledgeTreeContract.Presenter = KnowledgeTreePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_knowledge_tree
    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener)
        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = knowledgeTreeAdapter
            itemAnimator = DefaultItemAnimator()
            recyclerViewItemDecoration?.let {
                addItemDecoration(it)
            }
        }

        knowledgeTreeAdapter.run {
            bindToRecyclerView(recyclerView)
            setEnableLoadMore(false)
            onItemClickListener = this@KnowledgeTreeFragment.onItemClickListener

        }

    }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestKnowledgeTree()
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        mPresenter?.requestKnowledgeTree()
    }

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20)
                scrollToPosition(0)
            else
                smoothScrollToPosition(0)
        }

    }

    override fun hideLoading() {
        super.hideLoading()
        swipeRefreshLayout?.isRefreshing = false
        knowledgeTreeAdapter.loadMoreComplete()

    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
        knowledgeTreeAdapter.loadMoreFail()
    }

    override fun setKnowledgeTree(lists: List<KnowledgeTreeBody>) {
        knowledgeTreeAdapter.run {
            replaceData(lists)
        }

        if (knowledgeTreeAdapter.data.isNotEmpty())
            mLayoutStatusView?.showContent()
        else
            mLayoutStatusView?.showEmpty()
    }


    companion object {
        fun getInstance(): KnowledgeTreeFragment {
            return KnowledgeTreeFragment()
        }
    }

    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            val data = datas[position]
            Intent(activity, KnowledgeActivity::class.java).run {
                putExtra(Constant.CONTENT_TITLE_KEY, data.name)
                putExtra(Constant.CONTENT_DATA_KEY, data)
                startActivity(this)
            }
        }
    }
}
