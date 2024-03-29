package com.example.wanandroidkotlin.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.cxz.wanandroid.base.IPresenter
import com.cxz.wanandroid.base.IView
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus

abstract class BasePresenter<M : IModel, V : IView> : IPresenter<V>, LifecycleObserver {
    protected var mModel: M? = null
    protected var mView: V? = null
    private val isViewAttached: Boolean get() = mView != null
    private var mCompositeDisposable: CompositeDisposable? = null
    /**
     * 创建 Model
     */
    open fun createModel(): M? = null

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false

    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleOwner) {
                (mModel as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)

            }
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun detachView() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        // 保证activity结束时取消所有正在执行的订阅
        unDispose()
        mModel?.onDetach()
        this.mModel = null
        this.mView = null
        this.mCompositeDisposable = null
    }


    private fun unDispose() {
        mCompositeDisposable?.clear()  // 保证Activity结束时取消
        mCompositeDisposable = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        // detachView()
        owner.lifecycle.removeObserver(this)
    }

    private class MvpViewNotAttachedException internal constructor() :
        RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

}