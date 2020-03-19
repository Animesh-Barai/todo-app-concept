package com.github.naz013.todoappconcept.home.presenter

import com.github.naz013.todoappconcept.arch.BasePresenter
import com.github.naz013.todoappconcept.data.DateRange
import com.github.naz013.todoappconcept.home.view.HomeView

interface HomePresenter : BasePresenter<HomeView> {
    fun loadDates()
    fun loadEvents(dateRange: DateRange)
    fun reloadEvents()
    fun addButtonClick()
}