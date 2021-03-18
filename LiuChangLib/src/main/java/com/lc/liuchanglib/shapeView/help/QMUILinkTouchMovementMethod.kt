package com.lc.liuchanglib.shapeView.help

import android.text.Layout
import android.text.Selection
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import com.lc.liuchanglib.BuildConfig
import java.lang.ref.WeakReference

/**
 *
 * @PackAge：com.lc.liuchanglib.shapeView.help
 * @创建人：Lc
 * @Desc：
 * @Time: 三月
 *
 **/
class QMUILinkTouchMovementMethod private constructor(): LinkMovementMethod() {
    private var mPressedSpanRf: WeakReference<ITouchableSpan>? = null
    companion object{
        private val sInstance: QMUILinkTouchMovementMethod? = null
        fun getInstance() = sInstance?:QMUILinkTouchMovementMethod()
    }
    override fun onTouchEvent(textView: TextView, buffer: Spannable, event: MotionEvent?): Boolean {

        return if (event!!.action == MotionEvent.ACTION_DOWN) {
            val span: ITouchableSpan? = getPressedSpan(textView, buffer, event)
            if (span != null) {
                span.setPressed(true)
                Selection.setSelection(
                    buffer, buffer.getSpanStart(span),
                    buffer.getSpanEnd(span)
                )
                mPressedSpanRf = WeakReference(span)
            }
            if (textView is ISpanTouchFix) {
                val tv = textView as ISpanTouchFix
                tv.setTouchSpanHit(span != null)
            }
            span != null
        } else if (event!!.action == MotionEvent.ACTION_MOVE) {
            val touchedSpan: ITouchableSpan? = getPressedSpan(textView, buffer, event)
            var recordSpan: ITouchableSpan? = null
            if (mPressedSpanRf != null) {
                recordSpan = mPressedSpanRf!!.get()
            }
            if (recordSpan != null && recordSpan !== touchedSpan) {
                recordSpan.setPressed(false)
                mPressedSpanRf = null
                recordSpan = null
                Selection.removeSelection(buffer)
            }
            if (textView is ISpanTouchFix) {
                val tv = textView as ISpanTouchFix
                tv.setTouchSpanHit(recordSpan != null)
            }
            recordSpan != null
        } else if (event!!.action === MotionEvent.ACTION_UP) {
            var touchSpanHint = false
            var recordSpan: ITouchableSpan? = null
            if (mPressedSpanRf != null) {
                recordSpan = mPressedSpanRf!!.get()
            }
            if (recordSpan != null) {
                touchSpanHint = true
                recordSpan.setPressed(false)
                if (event!!.action === MotionEvent.ACTION_UP) {
                    recordSpan.onClick(textView)
                }
            }
            mPressedSpanRf = null
            Selection.removeSelection(buffer)
            if (textView is ISpanTouchFix) {
                val tv = textView as ISpanTouchFix
                tv.setTouchSpanHit(touchSpanHint)
            }
            touchSpanHint
        } else {
            var recordSpan: ITouchableSpan? = null
            if (mPressedSpanRf != null) {
                recordSpan = mPressedSpanRf!!.get()
            }
            if (recordSpan != null) {
                recordSpan.setPressed(false)
            }
            if (textView is ISpanTouchFix) {
                val tv = textView as ISpanTouchFix
                tv.setTouchSpanHit(false)
            }
            mPressedSpanRf = null
            Selection.removeSelection(buffer)
            false
        }

    }

    fun getPressedSpan(
        textView: TextView,
        spannable: Spannable,
        event: MotionEvent
    ): ITouchableSpan? {
        var x = event.x
        var y = event.y.toInt()
        x -= textView.totalPaddingLeft
        y -= textView.totalPaddingTop
        x += textView.scrollX
        y += textView.scrollY
        val layout: Layout = textView.layout
        val line: Int = layout.getLineForVertical(y)

        /*
         * BugFix: https://issuetracker.google.com/issues/113348914
         */try {
            var off: Int = layout.getOffsetForHorizontal(line, x)
            if (x < layout.getLineLeft(line) || x > layout.getLineRight(line)) {
                // 实际上没点到任何内容
                off = -1
            }
            val link = spannable.getSpans(off, off, ITouchableSpan::class.java)
            var touchedSpan: ITouchableSpan? = null
            if (link.size > 0) {
                touchedSpan = link[0]
            }
            return touchedSpan
        } catch (e: IndexOutOfBoundsException) {
            if (BuildConfig.DEBUG) {
                Log.d(this.toString(), "getPressedSpan", e)
            }
        }
        return null
    }
}