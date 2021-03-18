package com.lc.liuchanglib.shapeView.help

import android.view.View

interface ITouchableSpan {
    fun setPressed(pressed: Boolean)
    fun onClick(widget: View?)
}
