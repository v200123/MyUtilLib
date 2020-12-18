package com.lc.liuchanglib.view

import android.content.Context
import android.icu.util.Measure
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 *
 * @PackAge：com.lc.liuchanglib.view
 * @创建人：admin
 * @Desc：任意布局的容器
 * @Time: 十二月
 *
 **/
class AnyViewContainer @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val  mLeftView by lazy { LinearLayout(context) }
    private val  mContentView by lazy { LinearLayout(context) }
    private val  mRightView by lazy { LinearLayout(context) }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val mWidth = MeasureSpec.getSize(widthMeasureSpec)
        val mHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        if(mWidthMode != MeasureSpec.AT_MOST)
        {
            throw IllegalArgumentException("不支持指定宽度")
        }else{
            val mTotalValue = mLeftView.measuredWidth + mContentView.measuredWidth + mRightView.measuredWidth
            if(mTotalValue>mWidth)
            {
                //首先先打算缩减中间的控件
                mContentView
            }
        }
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {

    }
}