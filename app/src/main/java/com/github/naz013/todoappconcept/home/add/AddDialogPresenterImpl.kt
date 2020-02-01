package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.data.dao.FolderDao
import com.github.naz013.todoappconcept.utils.threading.SchedulerProvider
import javax.inject.Inject

class AddDialogPresenterImpl @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val eventDao: EventDao,
    private val folderDao: FolderDao
) : AddDialogPresenter {

    private var addDialogView: AddDialogView? = null

    override fun saveTask(addTaskForm: AddTaskForm) {
        if (!verifyForm(addTaskForm)) {
            return
        }

        addDialogView?.closeDialog()
    }

    private fun verifyForm(addTaskForm: AddTaskForm): Boolean {
        if (addTaskForm.title.isBlank()) {
            addDialogView?.showError("Empty title")
            return false
        }
        if (addTaskForm.time != null && addTaskForm.date == null) {
            addDialogView?.showError("Date is not specified")
            return false
        }
        return true
    }

    override fun setView(v: AddDialogView) {
        addDialogView = v
    }

    override fun onDestroy() {
        addDialogView = null
    }
}