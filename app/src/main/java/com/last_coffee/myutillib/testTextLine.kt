package com.last_coffee.myutillib

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.orhanobut.logger.Logger
import java.lang.Math.*

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 二月
 *
 **/
class testTextLine @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val baseLine:Float
    init {
        paint.textSize = 150F
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.parseColor("#FFFFFF")
        baseLine = paint.fontMetrics.bottom - paint.fontMetrics.top - paint.fontMetrics.descent
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Logger.d("当前的字体的top为：${paint.fontMetrics.top}")
        Logger.d("当前的字体的leading为：${paint.fontMetrics.leading}")
        Logger.d("当前的字体的ascent为：${paint.fontMetrics.ascent}")
        Logger.d("当前的字体的baseline为：${baseLine}")
        Logger.d("当前的字体的descent为：${paint.fontMetrics.descent}")
        Logger.d("当前的字体的bottom为：${paint.fontMetrics.bottom}")
        canvas.save()
        canvas.translate(0F, baseLine)
//        canvas.drawText("abcdeg",0f,baseLine,paint)
        paint.color = Color.BLUE
        canvas.drawText("AaBbCcDdEeGg",0f,0f,paint)
        paint.color = Color.BLACK
        canvas.drawLine(0f,paint.fontMetrics.top,width.toFloat(),paint.fontMetrics.top,paint)
        paint.color = Color.CYAN
        canvas.drawLine(0f,paint.fontMetrics.leading,width.toFloat(),paint.fontMetrics.leading,paint)
        paint.color = Color.DKGRAY
        canvas.drawLine(0f,paint.fontMetrics.ascent,width.toFloat(),paint.fontMetrics.ascent,paint)
        paint.color = Color.GREEN
        canvas.drawLine(0f,paint.fontMetrics.descent,width.toFloat(),paint.fontMetrics.descent,paint)
        paint.color = Color.LTGRAY
        canvas.drawLine(0f,paint.fontMetrics.bottom,width.toFloat(),paint.fontMetrics.bottom,paint)
    }

}