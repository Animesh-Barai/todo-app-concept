package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.arch.BasePresenter

interface AddDialogPresenter : BasePresenter<AddDialogView> {
    fun saveTask(addTaskForm: AddTaskForm)
}