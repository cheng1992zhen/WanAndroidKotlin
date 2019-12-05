package com.example.wanandroidkotlin.event

import com.example.wanandroidkotlin.utils.SettingUtil



class ColorEvent(var isRefresh: Boolean, var color: Int = SettingUtil.getColor())