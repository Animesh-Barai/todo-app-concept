package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.arch.BasePresenter
import com.github.naz013.todoappconcept.data.Folder
import java.util.*

interface AddDialogPresenter : BasePresenter<AddDialogView> {
    fun saveTask(addTaskForm: AddTaskForm)
    fun loadDates()
    fun loadTimes()
    fun loadFolders()
    fun pickDate(date: Date)
    fun removeDate()
    fun pickTime(date: Date)
    fun removeTime()
    fun pickFolder(folder: Folder)
    fun removeFolder()
}