package com.pince.module_tab.customtab


import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

/**
 * @Author Yu
 * @Date 2021/1/18 15:43
 * @Description 自定义 底部导航栏
 */
class BottomTabLayoutView : LinearLayout {
    private var mContext: Context? = null
    lateinit var mAdapter: BaseTabAdapter
    lateinit var viewPager: ViewPager
    private var selectItem = 0//默认选中的item

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        mContext = context
    }

    fun initBottomTabData(
        adapter: BaseTabAdapter,
        viewPager: ViewPager,
        selectItem: Int
    ) {
        removeAllViews()
        orientation = HORIZONTAL
        this.mAdapter = adapter
        this.viewPager = viewPager
        this.selectItem = selectItem
        initViewPager(viewPager)

        post {
            for (index in 0 until mAdapter.getCount()) {
                var view = mAdapter.setViewToPosition(mContext!!, index)
                var widthScale = MeasureSpec.getSize(width) / (mAdapter.getCount())
                addView(view, widthScale, MeasureSpec.getSize(height))
            }
            viewPager.currentItem = selectItem
            mAdapter.onPageSelected(selectItem)
        }
    }

    private fun initViewPager(mViewPager: ViewPager) {
        mAdapter.addPagerScrollListener(viewPager = mViewPager) {
        }
    }
}
