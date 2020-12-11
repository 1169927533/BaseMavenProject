package com.example.smart.smartpack.base.fragme

import android.os.Bundle

/**
 *Create time 2020/8/27
 *Create Yu
 *description:
 */
abstract class BaseVmMeFragment: BaseFrameFragment()  {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData()
        initViewData()
    }


    abstract fun observeLiveData()


    abstract fun initViewData()


}