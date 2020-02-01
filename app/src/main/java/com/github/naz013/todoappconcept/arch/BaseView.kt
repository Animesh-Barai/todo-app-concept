package com.github.naz013.todoappconcept.arch

interface BaseView {
    fun showMessage(message: String)
    fun showError(message: String)
}