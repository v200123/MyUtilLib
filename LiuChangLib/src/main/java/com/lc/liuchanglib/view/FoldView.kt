package com.lc.liuchanglib.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt

/**
 *
 * @PackAge：com.lc.liuchanglib.view
 * @创建人：Lc
 * @Desc：
 * @Time: 二月
 *
 **/
class FoldView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var isOpen = false
    private val mHeadView = FrameLayout(context)
    private val mFootView = FrameLayout(context)
    private val mContentText = TextView(context)
    init {
        this.gravity = VERTICAL
        addView(mHeadView)
        addView(mContentText)
        addView(mFootView)
    }
    public fun setContent(message:CharSequence,textSize:Float = 35f,@ColorInt contentTextColor:Int = Color.RED){
        mContentText.text = message
        mContentText.textSize = textSize
        mContentText.setTextColor(contentTextColor)

    }




}