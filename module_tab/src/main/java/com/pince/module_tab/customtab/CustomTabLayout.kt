package com.pince.module_tab.customTabv


import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import com.pince.module_tab.R
import com.pince.module_tab.customtab.BaseTabAdapter
import com.pince.module_tab.customtab.ItemDataBean

/**
 *Create time 2020/9/11
 *Create Yu
 *description:自定义顶部导航栏
 */
class CustomTabLayout : RelativeLayout {

    var navWidth = 0
    var widthSpace: Int = 0
    lateinit var navLineView: View
    lateinit var mAdapter: BaseTabAdapter
    var hasBottomLine = false //是否有底部条
    var average = false //是否平均每个item
    var itemMarginRight = 0f//每个item距离右边的距离
    var bottomlinewidth = 0f//底部线的宽度
    var bottomlineheight = 0f//底部线的高度
    var bottomlinecolor = 0//底部线的高度
    var horizontalScrollView: CusHorizontalScrollView? = null
    var mContext: Context? = null
    var itemWidthList = ArrayList<ItemDataBean>()
    var theWidth = 0

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        mContext = context
        var typeArray = context.obtainStyledAttributes(attributeSet,R.styleable.customTabv)
        hasBottomLine = typeArray.getBoolean(R.styleable.customTabv_hasBottomLine, true)
        average = typeArray.getBoolean(R.styleable.customTabv_average, true)
        itemMarginRight = typeArray.getDimension(R.styleable.customTabv_itemmarginright, 0f)
        bottomlinewidth = typeArray.getDimension(R.styleable.customTabv_bottomlinewidth, 0f)
        bottomlineheight = typeArray.getDimension(R.styleable.customTabv_bottomlineheight, 0f)
        bottomlinecolor = typeArray.getResourceId(
            R.styleable.customTabv_bottomlinecolor,
            R.drawable.shape_f04989
        )

        typeArray.recycle()
    }

    var view: ViewGroup? = null
    var view2: ViewGroup? = null
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        itemWidthList.clear()
        if (childCount > 0) {
            view = if (hasBottomLine && childCount > 1) {
                getChildAt(1) as ViewGroup
            } else {
                getChildAt(0) as ViewGroup
            }
            view?.let {
                if (it.childCount > 0) {
                    view2 = it.getChildAt(0) as ViewGroup
                }
            }
        }
        view2?.let {
            for (index in 0 until it.childCount) {
                var views = it.getChildAt(index)
                itemWidthList.add(
                    ItemDataBean(
                        views.measuredWidth,
                        theWidth,
                        theWidth + views.measuredWidth
                    )
                )
                theWidth += views.measuredWidth + itemMarginRight.toInt()
            }
            theWidth = 0
        }

    }

    fun setAdapter(adapter: BaseTabAdapter, viewPager: ViewPager, selectItem: Int) {
        mAdapter = adapter
        initViewPager(viewPager)

        removeAllViews()
        var titleLayout = LinearLayout(mContext)
        var layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        titleLayout.orientation = LinearLayout.HORIZONTAL
        layoutParams.gravity = Gravity.CENTER
        titleLayout.layoutParams = layoutParams

        for (index in 0 until adapter.getCount()) {
            var view = adapter.getView(mContext!!, index)
            if (index <= adapter.getCount() - 2) {
                view.layoutParams?.let {
                    var params = it as LinearLayout.LayoutParams
                    params.rightMargin = itemMarginRight.toInt()
                    view.layoutParams = params
                }
            }
            titleLayout.addView(view)
        }

        widthSpace = if (average) {
            ViewGroup.LayoutParams.MATCH_PARENT
        } else {
            ViewGroup.LayoutParams.WRAP_CONTENT
        }

        val lp1 = LayoutParams(widthSpace, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp1.addRule(CENTER_VERTICAL, TRUE)//是每个view垂直居中
        viewPager.currentItem = selectItem
        mAdapter.onPageSelected(selectItem)

        initBottomView()

        horizontalScrollView =
            CusHorizontalScrollView(
                mContext
            )
        horizontalScrollView!!.isFillViewport = true
        val layoutParams2 = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        horizontalScrollView!!.addView(titleLayout, layoutParams2)
        horizontalScrollView!!.isHorizontalScrollBarEnabled = false
        addView(horizontalScrollView, lp1)
    }

    private fun initBottomView() {
        if (hasBottomLine) {
            setNavLine(bottomlinewidth, bottomlineheight, bottomlinecolor, 0)
        }
    }

    /**
     *linWidth      一个item的宽度 如果为0 那么就平均一下屏幕宽度  否则用自己的宽度
     * lineHeight   一个item的高度
     * color        一个item的颜色
     * currentPosition 当前选中哪个
     */
    @SuppressLint("ResourceType")
    fun setNavLine(linWidth: Float, lineHeight: Float, color: Int, currentPosition: Int) {
        navWidth = if (linWidth == 0f) {
            width / mAdapter.getCount()
        } else {
            linWidth.toInt()
        }
        var navHeight = lineHeight.toInt()
        var layoutParams = RelativeLayout.LayoutParams(navWidth, navHeight)

        if (hasBottomLine) {
            navLineView.layoutParams = layoutParams
            val lp = LayoutParams(navWidth, navHeight)
            lp.addRule(ALIGN_PARENT_BOTTOM, TRUE)

            addView(navLineView, lp)
            moveBar(navLineView, navWidth, 0f, currentPosition)
        }
    }

    fun initBottomView(view: View) {
        navLineView = view
    }


    /**
     * line 需要移动的view
     * navWidth 一个view的宽度
     * percent  偏移量
     */
    fun moveBar(line: View, navWidth: Int, percent: Float, position: Int) {
        val lp = line.layoutParams as LayoutParams
        var widthh = (width - itemMarginRight * (mAdapter.getCount() - 1)) / mAdapter.getCount()
        var wioiii = (widthh + itemMarginRight)
        var marginleftt = position * wioiii + (wioiii * percent).toInt() + (widthh - navWidth) / 2

        lp.width = (navWidth - percent * 2).toInt()
        lp.setMargins((marginleftt + percent).toInt(), 0, percent.toInt(), 0)
        line.requestLayout()


    }

    private fun initViewPager(mViewPager: ViewPager) {
        mAdapter.addPagerScrollListener(viewPager = mViewPager) {
            onPagerScrolled = { position, positionOffset, positionOffsetPixels ->
                if (hasBottomLine) {
                    moveBar(navLineView, navWidth, positionOffset, position)
                }
                onPageScrollChanged(position, positionOffset)
            }
            onPageSelectedInLayout = { position ->
                onPageLayoutSelected(position)
            }
        }
    }

    private fun onPageLayoutSelected(position: Int) {
        if (itemWidthList.size > 0 && canSmoothToRight(position) && (position + 2) * itemWidthList[position].itemWidth > width) {

        }
    }

    private fun canSmoothToRight(position: Int): Boolean {
        return (mAdapter.getCount() - position) * itemWidthList[position].itemWidth >= width
    }

    private fun onPageScrollChanged(
        position: Int,
        positionOffset: Float
    ) {
        horizontalScrollView?.let {
            // 手指跟随滚动
            if (itemWidthList.size > 0 && position >= 0 && position < mAdapter.getCount()) {
                val currentPosition =
                    (mAdapter.getCount() - 1).coerceAtMost(position)
                val nextPosition =
                    (mAdapter.getCount() - 1).coerceAtMost(position + 1)

                /**
                 * startX 第一个item需要移动的距离
                 */
                var startX =
                    itemWidthList[currentPosition].leftDistance - it.width / 2 + itemWidthList[currentPosition].itemWidth / 2

                /**
                 * scrollDistance  下一个item中心 距离 上一个item中心 需要移动的距离
                 */
                var scrollDistance =
                    (itemWidthList[nextPosition].leftDistance - itemWidthList[currentPosition].leftDistance + itemWidthList[nextPosition].itemWidth / 2f - itemWidthList[currentPosition].itemWidth / 2f) * positionOffset
                it.scrollTo(
                    (startX + scrollDistance).toInt(),
                    0
                )

            }
        }


    }


    class CusHorizontalScrollView : HorizontalScrollView {
        private var onScrollChangedListener: OnScrollChangedListener? = null

        constructor(context: Context?) : super(context) {}
        constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        ) {
        }

        fun setOnScrollChangedListener(onScrollChangedListener: OnScrollChangedListener?) {
            this.onScrollChangedListener = onScrollChangedListener
        }

        override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
            super.onScrollChanged(l, t, oldl, oldt)
            if (onScrollChangedListener != null) {
                onScrollChangedListener!!.onScrollChanged(l, t, oldl, oldt)
            }
        }
    }

    interface OnScrollChangedListener {
        fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int)
    }
}