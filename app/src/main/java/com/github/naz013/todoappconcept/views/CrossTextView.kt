package com.github.naz013.todoappconcept.views

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class CrossTextView : AppCompatTextView {

    private var isCrossed = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle)

    fun crossText() {
        if (isCrossed) return
        isCrossed = true
        refreshStyle()
    }

    fun plainText() {
        if (!isCrossed) return
        isCrossed = false
        refreshStyle()
    }

    override fun onSaveInstanceState(): Parcelable? {
        val savedInstance = Bundle()
        savedInstance.putParcelable("super", super.onSaveInstanceState())
        savedInstance.putBoolean("state", isCrossed)
        return savedInstance
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            isCrossed = state.getBoolean("state", false)
            super.onRestoreInstanceState(state.getParcelable("super"))
        } else {
            super.onRestoreInstanceState(state)
        }
        refreshStyle()
    }

    private fun refreshStyle() {
        paintFlags = if (isCrossed) {
            paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            paintFlags or -Paint.STRIKE_THRU_TEXT_FLAG
        }
    }
}