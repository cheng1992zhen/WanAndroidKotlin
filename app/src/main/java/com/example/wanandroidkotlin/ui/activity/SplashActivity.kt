package com.example.wanandroidkotlin.ui.activity

import android.animation.Animator
import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    private var alphaAnimation: AlphaAnimation? = null
    override fun useEventBus(): Boolean = false
    override fun attachLayoutRes() = R.layout.activity_splash

    override fun initData() {

    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation?.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })
        }
        layout_splash.animation = alphaAnimation
    }

    override fun start() {

    }

    override fun initColor() {
        super.initColor()
        layout_splash.setBackgroundColor(mThemeColor)
    }

    fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
