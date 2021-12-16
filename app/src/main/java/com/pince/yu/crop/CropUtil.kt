package com.pince.yu.crop

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import kotlin.math.floor

/**
 * @Author Yu
 * @Date 2021/5/21 9:13
 * @Description 图片操作工具类
 */
object CropUtil {

    /**
     * maxWidth 目标最大宽度
     */
    fun comPressBitMapToFit(context: Context, resourceId: Int, maxWidth: Int): Bitmap {
        var options = BitmapFactory.Options()
        options.inJustDecodeBounds = true //设置只读取图片得宽高 不加载到内存
        BitmapFactory.decodeResource(context.resources, resourceId, options)
        var bitmapWidth = options.outWidth
        var inSampleSize = 1 //对图片体积得压缩
        if (bitmapWidth > maxWidth) {
            var widthRadio = floor((bitmapWidth.toFloat() / maxWidth.toDouble()))
            inSampleSize = widthRadio.toInt()
        }
        options.inSampleSize = inSampleSize
        options.inJustDecodeBounds = false
        return scaleBitmapToWidth(
            BitmapFactory.decodeResource(
                context.resources,
                resourceId,
                options
            ), maxWidth, 0
        )
    }

    /**
     * bitmap 原图片
     * newWidth 新得宽度
     * degree 旋转角度
     */
    fun scaleBitmapToWidth(bitmap: Bitmap, newWidth: Int, degree: Int): Bitmap {
        var width = bitmap.width
        var height = bitmap.height
        //如果旋转得角度为90 或 270 需要颠倒宽高值
        if (is90And270(degree)) {
            width = bitmap.height
            height = bitmap.width
        }
        var initScale = 1f
        if (newWidth < width) {
            initScale = newWidth * 1.0f / width
        }
        return createBitmapForMatrix(bitmap, initScale, degree)
    }

    private fun is90And270(degree: Int): Boolean {
        return degree > 0 && (degree == 90 || degree % 270 == 0)
    }

    /**
     * bitmap 目标图片
     * initScale 缩放大小
     * degree 旋转角度
     */
    fun createBitmapForMatrix(bitmap: Bitmap, initScale: Float, degree: Int): Bitmap {
        var matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        var scale = if (initScale <= 0f) {
            0.01f
        } else {
            initScale
        }
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    //保存图片
    fun saveBitMap(context: Context, bitmap: Bitmap) {
        var filePath = context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS)!!.absolutePath
        var file = File(filePath, "${System.currentTimeMillis()}.png")
        var fileOutPutStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutPutStream)
        fileOutPutStream.flush()
        fileOutPutStream.close()
    }
}