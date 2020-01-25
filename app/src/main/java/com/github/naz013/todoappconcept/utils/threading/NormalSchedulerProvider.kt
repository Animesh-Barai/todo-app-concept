package com.github.naz013.todoappconcept.utils.threading

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NormalSchedulerProvider : SchedulerProvider {
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()!!
    override fun io() = Schedulers.io()
}