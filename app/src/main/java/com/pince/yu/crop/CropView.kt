package com.pince.yu.crop

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.ViewUtils
import com.uppack.lksmall.baseyu.weight.util.ViewUtil

/**
 * @Author Yu
 * @Date 2021/5/21 11:09
 * @Description 裁切
 */
class CropView : View {
    // 展示得view
    var showBitmap: Bitmap? = null

    // 图片初始化完成后的矩阵
    lateinit var showBitmapRectF: RectF

    // 控制图片绘制的矩阵
    var showBitmapMatrix: Matrix = Matrix()

    // 初始化图片的缩放平移 保证图片在页面中心
    var initScale = 1f
    var initTranslateX = 0f
    var initTranslateY = 0f

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        showBitmapMatrix.postScale(initScale, initScale)
        showBitmapMatrix.postTranslate(initTranslateX, initTranslateY)
        showBitmapMatrix.mapRect(showBitmapRectF)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)
        var width = ViewUtil.getScreenWidth(context) / 2
        var height = ViewUtil.getScreenHeight(context) / 2
        if (ViewGroup.LayoutParams.WRAP_CONTENT == layoutParams.width && ViewGroup.LayoutParams.WRAP_CONTENT == layoutParams.height) {
            setMeasuredDimension(width, height)
        } else if (ViewGroup.LayoutParams.WRAP_CONTENT == layoutParams.width) {
            setMeasuredDimension(width, heightSize)
        } else if (ViewGroup.LayoutParams.WRAP_CONTENT == layoutParams.height) {
            setMeasuredDimension(widthSize, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (showBitmap == null) {
            return
        }
       // canvas.draB
    }
}