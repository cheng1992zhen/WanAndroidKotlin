package com.example.wanandroidkotlin.utils

import com.cxz.wanandroid.base.IView
import com.cxz.wanandroid.mvp.model.bean.BaseBean
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.app.App
import com.example.wanandroidkotlin.base.IModel
import com.example.wanandroidkotlin.http.RetryWithDelay
import com.example.wanandroidkotlin.http.exception.ErrorStatus
import com.example.wanandroidkotlin.http.exception.ExceptionHandle
import com.example.wanandroidkotlin.rx.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


fun <T : BaseBean> Observable<T>.ss(
    model: IModel?,
    view: IView?, isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe(object : Observer<T> {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onSubscribe(d: Disposable) {
                if (isShowLoading) view?.showLoading()
                model?.addDisposable(d)
                if (!NetWorkUtil.isNetworkConnected(App.context)) {
                    view?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
                    onComplete()
                }
            }

            override fun onNext(t: T) {
                when {
                    t.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(t)
                    t.errorCode == ErrorStatus.TOKEN_INVALID -> {
                        // Token 过期，重新登录
                    }
                    else -> view?.showDefaultMsg(t.errorMsg)
                }
            }

            override fun onError(e: Throwable) {
                view?.hideLoading()
                view?.showError(ExceptionHandle.handleException(e))
            }
        })
}

fun <T : BaseBean> Observable<T>.sss(
    view: IView?,
    isShowLoading: Boolean = true,
    onSuccess: (T) -> Unit,
    onError: ((T) -> Unit)? = null
): Disposable {
    if (isShowLoading) view?.showLoading()
    return this.compose(SchedulerUtils.ioToMain())
        .retryWhen(RetryWithDelay())
        .subscribe({
            when {
                it.errorCode == ErrorStatus.SUCCESS -> onSuccess.invoke(it)
                it.errorCode == ErrorStatus.TOKEN_INVALID -> {
                    // Token 过期，重新登录
                }
                else -> {
                    if (onError != null) {
                        onError.invoke(it)
                    } else {
                        if (it.errorMsg.isNotEmpty())
                            view?.showDefaultMsg(it.errorMsg)
                    }
                }
            }
        }, {
            view?.hideLoading()
            view?.showError(ExceptionHandle.handleException(it))
        })
}