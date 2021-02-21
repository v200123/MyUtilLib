package com.lc.liuchanglib.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
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
    public var mMaxLine: Int = 2
    public var lineCount = 0
        get() {
       return mContentText.lineCount
    }
    init {
        this.gravity = VERTICAL
        addView(mHeadView)
        addView(mContentText)
        addView(mFootView)
    }

    public fun setContent(message: CharSequence, textSize: Float = 35f, @ColorInt contentTextColor: Int = Color.RED) {
        mContentText.text = message
        mContentText.textSize = textSize
        mContentText.setTextColor(contentTextColor)
        //获取当前的列数 需要在绘制完成后才能获取
        post {

            Log.d("LiuChang", "当前的列数为:$lineCount 单列的高度为:${mContentText.lineHeight}；总的高度为：${mContentText.height}")
            if (lineCount < mMaxLine) {
                isOpen = true
                mFootView.visibility = View.GONE
            } else {
                isOpen = false
                mContentText.maxLines = mMaxLine
                mFootView.visibility = View.VISIBLE
            }
        }
        invalidate()
    }

    fun openAnim(){
        mContentText.maxLines = if(isOpen) mMaxLine else lineCount
        isOpen  = !isOpen
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }


}