package com.example.wanandroidkotlin.ui.fragment


import com.cxz.wanandroid.mvp.model.bean.ArticleResponseBody
import com.cxz.wanandroid.mvp.model.bean.Banner
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseMvpActivity
import com.example.wanandroidkotlin.base.BaseMvpFragment
import com.example.wanandroidkotlin.mvp.contract.HomeContract
import com.example.wanandroidkotlin.mvp.presenter.HomePresenter

/**
 *
 */
class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(),
    HomeContract.View {
    override fun lazyLoad() {

    }

    override fun createPresenter(): HomeContract.Presenter = HomePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun scrollToTop() {
    }

    override fun setBanner(banners: List<Banner>) {
    }

    override fun setArticles(articles: ArticleResponseBody) {
    }

    override fun showCollectSuccess(success: Boolean) {
    }

    override fun showCancelCollectSuccess(success: Boolean) {
    }

    companion object {
        fun getInstance(): HomeFragment {
            return HomeFragment()
        }
    }


}
