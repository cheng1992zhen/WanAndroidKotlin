package com.example.wanandroidkotlin.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.wanandroidkotlin.R

/**
 * A simple [Fragment] subclass.
 */
class ProjectListFragment : Fragment() {
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_list, container, false)
    }

    companion object {
        fun getInstance(id: Int): ProjectListFragment {
            val fragment = ProjectListFragment()
            val args = Bundle()
            args.putInt("", id)
            fragment.arguments = args
            return fragment
        }
    }

//    override fun scrollToTop() {
//        recyclerView.run {
//            if (linearLayoutManager.findFirstVisibleItemPosition() > 20) {
//                scrollToPosition(0)
//            } else {
//                smoothScrollToPosition(0)
//            }
//        }
//    }

}
