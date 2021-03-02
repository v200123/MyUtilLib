package com.lc.liuchanglib.view

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.RotateDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.View.MeasureSpec.*
import android.view.animation.RotateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Space
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.children
import androidx.core.view.get
import com.lc.liuchanglib.R
import com.lc.liuchanglib.shapeView.ShapeFrameLayout
import com.lc.liuchanglib.shapeView.ShapeLinearLayout
import com.lc.liuchanglib.shapeView.ShapeTextView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.OnSelectListener

/**
 *
 * @PackAge：com.lc.liuchanglib.view
 * @创建人：Lc
 * @Desc：
 * @Time: 二月
 *
 **/
class DropDownTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShapeLinearLayout(context, attrs, defStyleAttr) {
    private var mAnimalAngle = 0f
    private var isOpen = false
    private val mContentTextView by lazy { AppCompatTextView(context) }
    private var mImageHeight:Float = 0f
    private var mImageWeight:Float = 0f
    init {
        addView(mContentTextView)
        val obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DropDownTextView)
         mImageHeight = obtainStyledAttributes.getDimension(R.styleable.DropDownTextView_image_height,0f)
         mImageWeight = obtainStyledAttributes.getDimension(R.styleable.DropDownTextView_image_width,0f)

    }
    var mRightDrawable: Drawable? = null
        set(value) {
            field = value
            if (this.children.all { it is ImageView }) {
                get(1) as ImageView
            } else {
                val imageView = AppCompatImageView(context).apply {
                    adjustViewBounds = true
                    setImageDrawable(field)
                    Log.d("LiuChang", "当前的高度为：${mImageWeight}")
                    layoutParams = LayoutParams(mImageWeight.toInt(), mImageHeight.toInt())
                            .apply {
                                Log.d("LiuChang", "当前的tv长度：${mContentTextView.width}")
                                marginStart = mContentTextView.width + 10
                                gravity = Gravity.CENTER_VERTICAL and Gravity.END
                            }
                }
                addView(imageView)
                invalidate()

            }
        }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        when(MeasureSpec.getMode(widthMeasureSpec)){
//            AT_MOST ->{
//                children.forEach {
//                    it.measuredWidth
//                }
//            }
//            EXACTLY ->{}
//            UNSPECIFIED ->{}
//
//        }
    }

    public fun <T> filterShow(dataList: Collection<T>, block: ((before: T) -> String), icons: IntArray?, listener: (position: Int, text: String) -> Unit) {
        val map = dataList.map { block(it) }.toTypedArray()
        XPopup.Builder(context)
                .atView(this)
                .offsetY(-10)
                .asAttachList(map, icons, listener)
                .show()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            showAnimal()
            isOpen = !isOpen
            return true
        }
        if(event.action == MotionEvent.ACTION_UP){
            performClick()
            return true

        }

        return super.onTouchEvent(event)
    }

    fun showAnimal() {
        if (childCount > 1) {
            if (!isOpen) {
                showOpenAnimal()
            } else {
                showCloseAnimal()
            }
        }


    }

    fun showOpenAnimal() {
        ObjectAnimator
                .ofFloat(getChildAt(1) as ImageView, "rotation", mAnimalAngle, 180f)
                .setDuration(200L).start()

    }

    fun showCloseAnimal() {
        ObjectAnimator
                .ofFloat(getChildAt(1) as ImageView, "rotation", 180f, 0f)
                .setDuration(200L).start()
    }

    fun setContextText(msg: CharSequence) {
        mContentTextView.text = msg
    }


}