package com.example.wanandroidkotlin.app

import android.app.Application
import android.content.Context
import android.os.Environment
import androidx.multidex.MultiDexApplication
import com.cxz.wanandroid.constant.Constant
import com.example.wanandroidkotlin.BuildConfig
import com.example.wanandroidkotlin.R
import com.example.wanandroidkotlin.utils.CommonUtil
import com.example.wanandroidkotlin.utils.showToast
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.upgrade.UpgradeStateListener
import com.tencent.bugly.crashreport.CrashReport
import kotlin.properties.Delegates

class App : MultiDexApplication() {
    companion object {
        private val TAG = "App"

        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application


        // 用户信息
      //  var userInfo: UserInfoBody? = null

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }

    /**
     * 初始化 Bugly
     */
    private fun initBugly() {
        if (BuildConfig.DEBUG) {
            return
        }
        // 获取当前包名
        val packageName = applicationContext.packageName
        // 获取当前进程名
        val processName = CommonUtil.getProcessName(android.os.Process.myPid())
        // 设置是否为上报进程
        val strategy = CrashReport.UserStrategy(applicationContext)
        strategy.isUploadProcess = false || processName == packageName
        // 设置sd卡的Download为更新资源保存目录;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        Beta.upgradeStateListener = object : UpgradeStateListener {
            override fun onDownloadCompleted(isManual: Boolean) {
            }

            override fun onUpgradeSuccess(isManual: Boolean) {
            }

            override fun onUpgradeFailed(isManual: Boolean) {
                if (isManual) {
                    showToast(getString(R.string.check_version_fail))
                }
            }

            override fun onUpgrading(isManual: Boolean) {
                if (isManual) {
                    showToast(getString(R.string.check_version_ing))
                }
            }

            override fun onUpgradeNoVersion(isManual: Boolean) {
                if (isManual) {
                    showToast(getString(R.string.check_no_version))
                }
            }
        }

        // 自定义更新布局要设置在 init 之前
        // R.layout.layout_upgrade_dialog 文件要注意两点
        // 注意1: 半透明背景要自己加上
        // 注意2: 即使自定义的弹窗不需要title, info等这些信息, 也需要将对应的tag标出出来, 一共有5个
        Beta.upgradeDialogLayoutId = R.layout.layout_upgrade_dialog

        // CrashReport.initCrashReport(applicationContext, Constant.BUGLY_ID, BuildConfig.DEBUG, strategy)
        Bugly.init(applicationContext, Constant.BUGLY_ID, BuildConfig.DEBUG, strategy)
    }
}
