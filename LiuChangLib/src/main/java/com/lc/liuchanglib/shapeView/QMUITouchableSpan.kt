package com.lc.liuchanglib.shapeView

import android.content.res.Resources
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import org.jetbrains.annotations.NotNull


/**
 *
 * @PackAge：com.lc.liuchanglib.shapeView
 * @创建人：Lc
 * @Desc：
 * @Time: 三月
 *
 **/
abstract class QMUITouchableSpan : ClickableSpan {
    private val mIsPressed = false

    @ColorInt
    private var mNormalBackgroundColor = 0

    @ColorInt
    private var mPressedBackgroundColor = 0

    @ColorInt
    private var mNormalTextColor = 0

    @ColorInt
    private var mPressedTextColor = 0

    private var mNormalBgAttr = 0
    private var mPressedBgAttr = 0
    private var mNormalTextColorAttr = 0
    private var mPressedTextColorAttr = 0

    private val mIsNeedUnderline = false

    abstract fun onSpanClick(widget: View?)
    override fun onClick(widget: View) {
        if (ViewCompat.isAttachedToWindow(widget)) {
            onSpanClick(widget)
        }
    }
    constructor(@ColorInt normalTextColor: Int,
                @ColorInt pressedTextColor: Int,
                @ColorInt normalBackgroundColor: Int,
                @ColorInt pressedBackgroundColor: Int) {
        mNormalTextColor = normalTextColor
        mPressedTextColor = pressedTextColor
        mNormalBackgroundColor = normalBackgroundColor
        mPressedBackgroundColor = pressedBackgroundColor
    }
    constructor( initFollowSkinView:View,
                 normalTextColorAttr:Int,pressedTextColorAttr:Int,
                normalBgAttr:Int, pressedBgAttr:Int) {
        mNormalBgAttr = normalBgAttr;
        mPressedBgAttr = pressedBgAttr;
        mNormalTextColorAttr = normalTextColorAttr;
        mPressedTextColorAttr = pressedTextColorAttr;
    }

    override fun updateDrawState(ds: TextPaint) {
        ds.color = if (mIsPressed) mPressedTextColor else mNormalTextColor
        ds.bgColor = if (mIsPressed) mPressedBackgroundColor else mNormalBackgroundColor
        ds.isUnderlineText = mIsNeedUnderline
    }

}