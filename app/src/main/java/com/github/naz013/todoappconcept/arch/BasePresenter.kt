package com.github.naz013.todoappconcept.arch

interface BasePresenter<V : BaseView> {
    fun setView(v: V)
    fun onDestroy()
}