package com.example.wanandroidkotlin.ui.activity

import android.app.Fragment
import android.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.afollestad.materialdialogs.color.ColorChooserDialog
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.base.BaseSwipeBackActivity
import com.example.wanandroidkotlin.event.ColorEvent
import com.example.wanandroidkotlin.utils.SettingUtil
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus

class SettingActivity : BaseSwipeBackActivity(), ColorChooserDialog.ColorCallback {


    private val EXTRA_SHOW_FRAGMENT = "show_fragment"
    private val EXTRA_SHOW_FRAGMENT_ARGUMENTS = "show_fragment_args"
    private val EXTRA_SHOW_FRAGMENT_TITLE = "show_fragment_title"

    override fun attachLayoutRes(): Int = R.layout.activity_setting


    override fun initData() {

    }

    override fun initView() {
        val initFragment: String = intent.getStringExtra(EXTRA_SHOW_FRAGMENT) ?: ""
        val initArguments: Bundle = intent.getBundleExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS) ?: Bundle()
        val initTitle: String = intent.getStringExtra(EXTRA_SHOW_FRAGMENT_TITLE)
            ?: resources.getString(R.string.setting)



        toolbar.run {
            title = initTitle
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupFragment(fragmentName: String, args: Bundle) {
        val fragment = Fragment.instantiate(this, fragmentName, args)
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.replace(R.id.container, fragment)
        transaction.commitAllowingStateLoss()
    }

    override fun start() {

    }

    override fun onColorSelection(dialog: ColorChooserDialog, selectedColor: Int) {
        if (!dialog.isAccentMode) {
            SettingUtil.setColor(selectedColor)
        }
        initColor()
        EventBus.getDefault().post(ColorEvent(true))
    }

    override fun onColorChooserDismissed(dialog: ColorChooserDialog) {

    }

}
