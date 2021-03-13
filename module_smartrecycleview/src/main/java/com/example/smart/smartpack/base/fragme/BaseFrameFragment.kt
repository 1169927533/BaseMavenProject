package com.example.smart.smartpack.base.fragme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/**
 *Create time 2020/8/27
 *Create Yu
 *description:
 */
abstract class BaseFrameFragment : androidx.fragment.app.Fragment() {


    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)

    }
    abstract fun showLoading(toShow: Boolean)

}