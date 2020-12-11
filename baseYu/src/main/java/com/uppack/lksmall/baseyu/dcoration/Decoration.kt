package com.uppack.lksmall.baseyu.dcoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.uppack.lksmall.baseyu.weight.util.ViewUtil

/**
 *Create time 2020/9/16
 *Create Yu
 *description:
 * 各个Adapter的 Decoration
 */
object Decoration {
    //新人指导界面
    class GuideItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val column = itemPosition % 2
            val margin = ViewUtil.dip2px(10f)
            val middle = ViewUtil.dip2px(4f)
            if (column == 0) {
                outRect.set(margin, margin, middle, 0)
            } else {
                outRect.set(middle, margin, margin, 0)
            }
        }
    }

    //热门界面顶部而那个recycleview
    class GridItemDecoration_Head : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val column = itemPosition % 2

            val margin = ViewUtil.dip2px(6f)
            val bottom = ViewUtil.dip2px(5f)
            val middle = ViewUtil.dip2px(2.5f)

            if (column == 0) {
                outRect.set(margin, bottom, middle, 0)

            } else {
                outRect.set(middle, bottom, margin, 0)

            }


        }
    }


    //热门界面
    class GridItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val column = itemPosition % 2
            val margin = ViewUtil.dip2px(3f)
            val bottom = ViewUtil.dip2px(6f)
            val middle = ViewUtil.dip2px(5f)

            if (column == 0) {
                outRect.set(middle, bottom, margin, 0)
            } else {
                outRect.set(margin, bottom, middle, 0)
            }
        }
    }
    class GridItemDecoration2 : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val column = itemPosition % 2
            val margin = ViewUtil.dip2px(5f)
            val bottom = ViewUtil.dip2px(8f)
            val middle = ViewUtil.dip2px(5f)
            if (column == -1) {
                outRect.set(margin, 0, middle, bottom)
            } else if (column == 0) {
                outRect.set(margin, 0, middle, bottom)
            } else {
                outRect.set(middle, 0, margin, bottom)
            }
        }
    }

    //首页 关注页
    class FollowItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val column = itemPosition % 2
            val margin = ViewUtil.dip2px(5f)
            val bottom = ViewUtil.dip2px(6f)
            val middle = ViewUtil.dip2px(3f)
            if (column == 0) {
                outRect.set(margin, bottom, middle, 0)
            } else {
                outRect.set(middle, bottom, margin, 0)
            }
        }
    }


    //搜索界面
    class SearchItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
            val topBottom = ViewUtil.dip2px(10f)
            if (itemPosition == 0) {
                outRect.set(0, 0, 0, topBottom)
            } else {
                outRect.set(0, topBottom, 0, topBottom)
            }
        }
    }

    /**
     * 礼物记录界面
     */
    class GiftItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val margin = ViewUtil.dip2px(30f)
            outRect.set(0, 0, 0, margin)
        }
    }
}