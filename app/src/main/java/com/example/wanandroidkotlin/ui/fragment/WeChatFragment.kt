package com.example.wanandroidkotlin.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.wanandroidkotlin.R

/**
 * A simple [Fragment] subclass.
 */
class WeChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_we_chat, container, false)
    }

    companion object {
        fun getInstance(): WeChatFragment {
            return WeChatFragment()
        }
    }

}
