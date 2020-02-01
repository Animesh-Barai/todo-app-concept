package com.github.naz013.todoappconcept.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.github.naz013.todoappconcept.R
import com.github.naz013.todoappconcept.utils.dp2px

class SuggestionTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initView(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        initView(context)
    }

    private fun initView(context: Context) {
        val padding = context.dp2px(4)
        val paddingStartEnd = context.dp2px(8)
        setPadding(paddingStartEnd, padding, paddingStartEnd, padding)
        setBackgroundResource(R.drawable.date_suggestion_bg)
    }
}