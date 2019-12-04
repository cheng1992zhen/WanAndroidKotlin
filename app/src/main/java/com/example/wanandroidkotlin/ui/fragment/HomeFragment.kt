package com.example.wanandroidkotlin.ui.fragment


import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.bingoogolapple.bgabanner.BGABanner
import com.cxz.wanandroid.constant.Constant
import com.cxz.wanandroid.mvp.model.bean.Article
import com.cxz.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cxz.wanandroid.mvp.model.bean.Banner
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseMvpFragment
import com.example.wanandroidkotlin.mvp.contract.HomeContract
import com.example.wanandroidkotlin.mvp.presenter.HomePresenter
import com.example.wanandroidkotlin.ui.activity.ContentActivity
import com.example.wanandroidkotlin.ui.adapter.HomeAdapter
import com.example.wanandroidkotlin.utils.ImageLoader
import com.example.wanandroidkotlin.widget.SpaceItemDecoration
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_banner.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 */
class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(),
    HomeContract.View {
    private var isRefresh = true
    /**
     * datas
     */
    private val datas = mutableListOf<Article>()
    private lateinit var bannerDatas: ArrayList<Banner>
    private var bannerView: View? = null
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    private val bannerAdapter: BGABanner.Adapter<ImageView, String> by lazy {
        BGABanner.Adapter<ImageView, String> { banner, itemView, model, position ->
            ImageLoader.load(activity, model, itemView)
        }
    }
    private val recyclerViewItemDecoration by lazy {
        activity?.let {
            SpaceItemDecoration(it)
        }
    }
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(activity, datas)
    }

    override fun initView(view: View) {
        super.initView(view)
        mLayoutStatusView = multiple_status_view
        swipeRefreshLayout.run {
            setOnRefreshListener(onRefreshListener)
        }

        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = homeAdapter
            itemAnimator = DefaultItemAnimator()
            recyclerViewItemDecoration?.let {
                addItemDecoration(it)
            }
        }

        bannerView = layoutInflater.inflate(R.layout.item_home_banner, null)
        bannerView?.banner?.run {
            setDelegate(bannerDelegate)
        }
        homeAdapter.run {
            addHeaderView(bannerView)
        }

    }

    private val bannerDelegate =
        BGABanner.Delegate<ImageView, String> { banner, imageView, model, position ->
            if (bannerDatas.size > 0) {
                val data = bannerDatas[position]
                Intent(activity, ContentActivity::class.java).run {
                    putExtra(Constant.CONTENT_URL_KEY, data.url)
                    putExtra(Constant.CONTENT_TITLE_KEY, data.title)
                    putExtra(Constant.CONTENT_ID_KEY, data.id)
                    startActivity(this)
                }
            }
        }

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestHomeData()
    }

    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun scrollToTop() {
    }

    override fun setBanner(banners: List<Banner>) {
        bannerDatas = banners as ArrayList<Banner>
        val bannerFeedList = ArrayList<String>()
        val bannerTitleList = ArrayList<String>()
        Observable.fromIterable(banners)
            .subscribe { list ->
                bannerFeedList.add(list.imagePath)
                bannerTitleList.add(list.title)

            }
        bannerView?.banner?.run {
            setAutoPlayAble(bannerFeedList.size > 1)
            setData(bannerFeedList, bannerTitleList)
            setAdapter(bannerAdapter)

        }
    }

    override fun setArticles(articles: ArticleResponseBody) {
        articles.datas.let {
            homeAdapter.run {
                if (isRefresh) {
                    replaceData(it)
                } else {
                    addData(it)
                }
                val size = it.size
                if (size < articles.size) {
                    loadMoreEnd(isRefresh)
                } else {
                    loadMoreComplete()
                }
            }
        }
        if (homeAdapter.data.isEmpty()) {
            mLayoutStatusView?.showEmpty()
        } else {
            mLayoutStatusView?.showContent()
        }
    }

    override fun showCollectSuccess(success: Boolean) {

    }

    override fun showCancelCollectSuccess(success: Boolean) {

    }

    override fun showError(errorMsg: String) {
        super.showError(errorMsg)
        mLayoutStatusView?.showError()
    }


    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {

    }


}
