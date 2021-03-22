package com.example.smart.smartpack.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.smart.smartpack.R
import com.example.smart.smartpack.util.SmartRefreshMeUtil
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.uppack.lksmall.baseyu.BaseActivity
import kotlinx.android.synthetic.main.activity_base_recycleview.*


/**
 *Create time 2020/8/19
 *Create Yu
 *description:
 */
abstract class BaseRecycleViewActivity<T> : BaseYuActivity() {

    //无数据展示效果
    open val emptyView: View by lazy {
        LayoutInflater.from(this).inflate(R.layout.layout_emptyview, null)
    }


    var clickFollowPosition = -1
    lateinit var smartRefreshUtil: SmartRefreshMeUtil<T>

    abstract val baseAdapter: BaseQuickAdapter<T, BaseViewHolder>
    abstract val onFetchListener: ((page: Int) -> Unit)
    lateinit var recycleview: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun isToolBarEnable(): Boolean {
        return false
    }

    override fun getToolBarTitle(): String {
        return "标题"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_base_recycleview
    }

    override fun initViewData() {
        frameLayout.addView(emptyView, 0)
        initRecycleviewLayout()
    }

    private fun initRecycleviewLayout() {
        //修改阻尼效果（0 - 1），越小阻尼越大，默认0.5
        smartRefreshLayout_fans.setDragRate(0.6f);
        smartRefreshLayout_fans.setRefreshHeader(ClassicsHeader(this))
        recycleview = recycleview_fans

        recycleview_fans.layoutManager = LinearLayoutManager(this)
        recycleview_fans.adapter = baseAdapter

        smartRefreshUtil = SmartRefreshMeUtil(
            baseAdapter, recycleview_fans, smartRefreshLayout_fans,
            true, true, 1, onFetchListener
        )

        smartRefreshUtil.startRefresh()//开启第一个加载数据
    }


    fun finishGetDataError() {
        emptyView.visibility = View.VISIBLE
        recycleview.visibility = View.GONE
    }

    fun finishGetDataSuccess() {
        emptyView.visibility = View.GONE
        recycleview.visibility = View.VISIBLE
    }
}