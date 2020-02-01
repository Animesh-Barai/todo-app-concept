package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.arch.BaseView

interface AddDialogView : BaseView {
    fun showError(message: String)
    fun showMessage(message: String)
    fun closeDialog()
}