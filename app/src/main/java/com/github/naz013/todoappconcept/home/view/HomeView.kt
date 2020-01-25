package com.github.naz013.todoappconcept.home.view

import com.github.naz013.todoappconcept.arch.BaseView
import com.github.naz013.todoappconcept.data.DateRange
import com.github.naz013.todoappconcept.data.FolderWithEvents
import com.github.naz013.todoappconcept.views.DateSelectorView

interface HomeView : BaseView {
    fun showDates(dates: List<DateSelectorView.DateItem<DateRange>>)
    fun showEvents(events: List<FolderWithEvents>)
    fun showMessage(message: String)
    fun showError(message: String)
}