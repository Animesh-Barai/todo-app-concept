package com.github.naz013.todoappconcept.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import androidx.annotation.Px
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.getDistinct(): LiveData<T> {
    val distinctLiveData = MediatorLiveData<T>()
    distinctLiveData.addSource(this, object : Observer<T> {
        private var initialized = false
        private var lastObj: T? = null
        override fun onChanged(obj: T?) {
            if (!initialized) {
                initialized = true
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            } else if ((obj == null && lastObj != null)
                || obj != lastObj) {
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            }
        }
    })
    return distinctLiveData
}

@Px
fun Context.dp2px(dp: Int): Int {
    val wm = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
    var display: Display? = null
    if (wm != null) {
        display = wm.defaultDisplay
    }
    val displayMetrics = DisplayMetrics()
    display?.getMetrics(displayMetrics)
    return (dp * displayMetrics.density + 0.5f).toInt()
}