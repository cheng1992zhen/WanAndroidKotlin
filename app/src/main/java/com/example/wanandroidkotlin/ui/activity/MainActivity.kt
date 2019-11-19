package com.example.wanandroidkotlin.ui.activity

import android.content.Intent
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentTransaction
import com.cxz.wanandroid.constant.Constant
import com.cxz.wanandroid.mvp.model.bean.UserInfoBody
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseActivity
import com.example.wanandroidkotlin.base.BaseMvpActivity
import com.example.wanandroidkotlin.mvp.contract.MainContract
import com.example.wanandroidkotlin.mvp.presenter.MainPresenter
import com.example.wanandroidkotlin.ui.fragment.*
import com.example.wanandroidkotlin.utils.Preference
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED
import com.google.android.material.internal.NavigationMenuItemView
import com.google.android.material.navigation.NavigationView
import com.tencent.bugly.beta.Beta
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    private val FRAGMENT_HOME = 0x01
    private val FRAGMENT_KNOWLEDGE = 0x02
    private val FRAGMENT_NAVIGATION = 0x03
    private val FRAGMENT_PROJECT = 0x04
    private val FRAGMENT_WECHAT = 0x05

    private var mHomeFragment: HomeFragment? = null
    private var mKnowledgeTreeFragment: KnowledgeTreeFragment? = null
    private var mNavigationFragment: NavigationFragment? = null
    private var mProjectFragment: ProjectFragment? = null
    private var mWeChatFragment: WeChatFragment? = null

    private var mIndex = FRAGMENT_HOME
    private var username: String by Preference(Constant.USERNAME_KEY, "")
    override fun attachLayoutRes() = R.layout.activity_main

    override fun initData() {
        Beta.checkUpgrade(false, false)
    }

    override fun createPresenter(): MainContract.Presenter = MainPresenter()

    override fun showLogoutSuccess(success: Boolean) {

    }

    override fun showUserInfo(bean: UserInfoBody) {

    }

    override fun initView() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
        bottom_navigation.run {
            // 以前使用 BottomNavigationViewHelper.disableShiftMode(this) 方法来设置底部图标和字体都显示并去掉点击动画
            // 升级到 28.0.0 之后，官方重构了 BottomNavigationView ，目前可以使用 labelVisibilityMode = 1 来替代
            // BottomNavigationViewHelper.disableShiftMode(this)
            labelVisibilityMode = LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }

        initDrawerLayout()

        initNavView()

        showFragment(mIndex)

        floating_action_btn.run {
            setOnClickListener(onFABClickListener)
        }
    }

    override fun start() {
        // 获取用户个人信息
        mPresenter?.getUserInfo()
    }

    private fun initDrawerLayout() {
        drawer_layout.run {
            var toggle = ActionBarDrawerToggle(
                this@MainActivity, this, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()

        }
    }

    private fun initNavView() {
        nav_view.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
            menu.findItem(R.id.nav_logout).isVisible = isLogin

        }
        tv_username?.run {
            text = if (!isLogin) getString(R.string.go_login) else username
            setOnClickListener {
                if (!isLogin) {
                    Intent(this@MainActivity, LoginActivity::class.java).run {
                        startActivity(this)
                    }
                }
            }
        }
    }

    private val onDrawerNavigationItemSelectedListener =
        NavigationView.OnNavigationItemSelectedListener { item ->
            return@OnNavigationItemSelectedListener when (item.itemId) {

                else -> return@OnNavigationItemSelectedListener false
            }

        }

    /**
     * NavigationItemSelect监听
     */
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            return@OnNavigationItemSelectedListener when (item.itemId) {
                R.id.action_home -> {
                    showFragment(FRAGMENT_HOME)
                    true
                }
                R.id.action_knowledge_system -> {
                    showFragment(FRAGMENT_KNOWLEDGE)
                    true
                }
                R.id.action_navigation -> {
                    showFragment(FRAGMENT_NAVIGATION)
                    true
                }
                R.id.action_project -> {
                    showFragment(FRAGMENT_PROJECT)
                    true
                }
                R.id.action_wechat -> {
                    showFragment(FRAGMENT_WECHAT)
                    true
                }
                else -> return@OnNavigationItemSelectedListener false

            }
        }

    private fun showFragment(index: Int) {
        var transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        mIndex = index
        when (mIndex) {
            FRAGMENT_HOME -> {
                if (mHomeFragment == null) {
                    toolbar.title = getString(R.string.app_name)
                    mHomeFragment = HomeFragment.getInstance()
                    transaction.add(R.id.container, mHomeFragment!!, "home")
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }
            FRAGMENT_KNOWLEDGE -> {
                toolbar.title = getString(R.string.knowledge_system)
                if (mKnowledgeTreeFragment == null) {
                    mKnowledgeTreeFragment = KnowledgeTreeFragment.getInstance()
                    transaction.add(R.id.container, mKnowledgeTreeFragment!!, "knowledge")
                } else {
                    transaction.show(mKnowledgeTreeFragment!!)
                }
            }
            FRAGMENT_NAVIGATION -> {
                toolbar.title = getString(R.string.navigation)
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavigationFragment.getInstance()
                    transaction.add(R.id.container, mNavigationFragment!!, "navigation")
                } else {
                    transaction.show(mNavigationFragment!!)
                }
            }
            FRAGMENT_PROJECT -> {
                toolbar.title = getString(R.string.project)
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.getInstance()
                    transaction.add(R.id.container, mProjectFragment!!, "project")
                } else {
                    transaction.show(mProjectFragment!!)
                }
            }
            FRAGMENT_WECHAT -> {
                toolbar.title = getString(R.string.wechat)
                if (mWeChatFragment == null) {
                    mWeChatFragment = WeChatFragment.getInstance()
                    transaction.add(R.id.container, mWeChatFragment!!, "wechat")
                } else {
                    transaction.show(mWeChatFragment!!)
                }
            }
        }
    }

    /**
     * FAB 监听
     */
    private val onFABClickListener = View.OnClickListener {

    }

    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mKnowledgeTreeFragment?.let { transaction.hide(it) }
        mNavigationFragment?.let { transaction.hide(it) }
        mProjectFragment?.let { transaction.hide(it) }
        mWeChatFragment?.let { transaction.hide(it) }
    }

    override fun initColor() {
        super.initColor()

    }
}
