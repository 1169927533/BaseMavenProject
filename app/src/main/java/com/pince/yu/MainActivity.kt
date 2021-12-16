package com.pince.yu

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.annotation.DrawableRes
import com.pince.yu.crop.CropUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //调整图片大小 最大设定为1080
        var bitmap = CropUtil.comPressBitMapToFit(this@MainActivity, R.drawable.desktop, 1080)
        btn_croppocture.setOnClickListener {
            img_end.setImageBitmap(bitmap)
        }
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val newRadius = progress * 1f / seekbar.max
                img_end.setImageBitmap(CropUtil.createBitmapForMatrix(bitmap, newRadius, 0))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }
}