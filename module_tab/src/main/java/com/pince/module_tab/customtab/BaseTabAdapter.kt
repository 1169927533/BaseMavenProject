package com.pince.module_tab.customtab

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

/**
 *Create time 2020/9/11
 *Create Yu
 *description: 导航栏的基类适配器
 */
abstract class BaseTabAdapter(private val item: List<Any>, private val viewPager: ViewPager) {
    var itemViewWidth = 0
    var targetListView = ArrayList<View>()

    //获取总个数
    open fun getCount(): Int {
        return  item.size
    }

    abstract fun getView(context: Context, position: Int): View //获取指定view

    fun setViewToPosition(context: Context, position: Int): View {
        var view = getView(context, position)
        targetListView.add(view)
        return view
    }

    open fun getAllTargetView(): List<View> {//获取全部指定View
        return targetListView
    }

    open fun clickItem(position: Int) {//item的点击事件
        viewPager.currentItem = position
    }


    open fun onPageScrollStateChangedd(state: Int) {}
    open fun onPageScrolledd(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
    abstract fun onPageSelected(position: Int) //当某个item被选中 该变状态


    var onPagerScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)? =
        null
    var onPageSelectedInLayout: ((position: Int) -> Unit)? = null

    fun addPagerScrollListener(viewPager: ViewPager, adapter: BaseTabAdapter.() -> Unit) {
        var adapter3 = this
        adapter.invoke(adapter3)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                onPageScrollStateChangedd(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                adapter3.onPagerScrolled?.let { it(position, positionOffset, positionOffsetPixels) }
                onPageScrolledd(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                adapter3.onPageSelectedInLayout?.let { it(position) }
                this@BaseTabAdapter.onPageSelected(position)
            }
        })
    }


}