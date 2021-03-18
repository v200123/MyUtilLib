package com.lc.liuchanglib.shapeView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.text.Spannable
import android.text.method.MovementMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.lc.liuchanglib.shapeView.help.IQMUILayout
import com.lc.liuchanglib.shapeView.help.ISpanTouchFix
import com.lc.liuchanglib.shapeView.help.QMUILinkTouchMovementMethod


/**
 *
 * @PackAge：com.lc.liuchanglib.shapeView
 * @创建人：Lc
 * @Desc：
 * @Time: 三月
 *
 **/
open class SpanTouchFixTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), ISpanTouchFix {
    init {
        highlightColor = Color.TRANSPARENT
    }
    /**
     * 记录当前 Touch 事件对应的点是不是点在了 span 上面
     */
    private var mTouchSpanHit = false

    /**
     * 记录每次真正传入的press，每次更改mTouchSpanHint，需要再调用一次setPressed，确保press状态正确
     */
    private var mIsPressedRecord = false

    /**
     * TextView是否应该消耗事件
     */
    private var mNeedForceEventToParent = false



    fun setNeedForceEventToParent(needForceEventToParent: Boolean) {
        mNeedForceEventToParent = needForceEventToParent
        isFocusable = !needForceEventToParent
        isClickable = !needForceEventToParent
        isLongClickable = !needForceEventToParent
    }

    /**
     * 使用者主动调用
     */
    fun setMovementMethodDefault() {
        setMovementMethodCompat(QMUILinkTouchMovementMethod.getInstance())
    }

    fun setMovementMethodCompat(movement: MovementMethod?) {
        movementMethod = movement
        if (mNeedForceEventToParent) {
            setNeedForceEventToParent(true)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (text !is Spannable || movementMethod !is QMUILinkTouchMovementMethod) {
            mTouchSpanHit = false
            return super.onTouchEvent(event)
        }
        mTouchSpanHit = true
        // 调用super.onTouchEvent,会走到QMUILinkTouchMovementMethod
        // 会走到QMUILinkTouchMovementMethod#onTouchEvent会修改mTouchSpanHint
        val ret = super.onTouchEvent(event)
        return if (mNeedForceEventToParent) {
            mTouchSpanHit
        } else ret
    }

    override fun setTouchSpanHit(hit: Boolean) {
        if (mTouchSpanHit != hit) {
            mTouchSpanHit = hit
            isPressed = mIsPressedRecord
        }
    }

    override fun performClick(): Boolean {
        return if (!mTouchSpanHit && !mNeedForceEventToParent) {
            super.performClick()
        } else false
    }

    override fun performLongClick(): Boolean {
        return if (!mTouchSpanHit && !mNeedForceEventToParent) {
            super.performLongClick()
        } else false
    }

    override fun setPressed(pressed: Boolean) {
        mIsPressedRecord = pressed
        if (!mTouchSpanHit) {
            onSetPressed(pressed)
        }
    }

    private fun onSetPressed(pressed: Boolean) {
        super.setPressed(pressed)
    }

}