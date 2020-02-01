package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.arch.BaseView
import com.github.naz013.todoappconcept.data.Folder
import java.util.*

interface AddDialogView : BaseView {
    fun closeDialog()
    fun showDateSuggestions(dates: List<Date>)
    fun showTimeSuggestions(times: List<Date>)
    fun showFolders(folders: List<Folder>)
    fun showSelectedDate(date: Date?)
    fun showSelectedTime(date: Date?)
    fun showSelectedFolder(folder: Folder?)
}