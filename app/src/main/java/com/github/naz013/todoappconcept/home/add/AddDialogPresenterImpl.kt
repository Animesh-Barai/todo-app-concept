package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.data.dao.FolderDao
import com.github.naz013.todoappconcept.utils.threading.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class AddDialogPresenterImpl @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val eventDao: EventDao,
    private val folderDao: FolderDao
) : AddDialogPresenter {

    private var view: AddDialogView? = null
    private var disposable: Disposable? = null

    override fun pickDate(date: Date) {
        view?.showSelectedDate(date)
        loadTimes()
    }

    override fun removeDate() {
        view?.showSelectedDate(null)
        view?.showSelectedTime(null)
        loadDates()
    }

    override fun pickTime(date: Date) {
        view?.showSelectedTime(date)
    }

    override fun removeTime() {
        view?.showSelectedTime(null)
        loadTimes()
    }

    override fun pickFolder(folder: Folder) {
        view?.showSelectedFolder(folder)
    }

    override fun removeFolder() {
        view?.showSelectedFolder(null)
        loadFolders()
    }

    override fun loadFolders() {
        disposable = folderDao.liveAll()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ view?.showFolders(it) }, { view?.showError(it.message ?: "") })
    }

    override fun loadDates() {
        disposable = generateDates(Date())
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ view?.showDateSuggestions(it) }, { view?.showError(it.message ?: "") })
    }

    override fun loadTimes() {
        disposable = generateTimes(Date())
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ view?.showTimeSuggestions(it) }, { view?.showError(it.message ?: "") })
    }

    override fun saveTask(addTaskForm: AddTaskForm) {
        if (!verifyForm(addTaskForm)) {
            return
        }

        view?.closeDialog()
    }

    private fun verifyForm(addTaskForm: AddTaskForm): Boolean {
        if (addTaskForm.title.isBlank()) {
            view?.showError("Empty title")
            return false
        }
        if (addTaskForm.time != null && addTaskForm.date == null) {
            view?.showError("Date is not specified")
            return false
        }
        return true
    }

    override fun setView(v: AddDialogView) {
        view = v
        view?.showSelectedTime(null)
        view?.showSelectedDate(null)
        view?.showSelectedFolder(null)
    }

    override fun onDestroy() {
        view = null
    }

    private fun generateTimes(from: Date): Observable<List<Date>> {
        return Observable.fromPublisher {
            val calendar = Calendar.getInstance()
            calendar.time = from
            calendar.set(Calendar.SECOND, 0)
            val list = mutableListOf<Date>()
            for (i in 0 until 4) {
                list.add(calendar.time)
                calendar.add(Calendar.HOUR_OF_DAY, 2)
            }
            it.onNext(list)
            it.onComplete()
        }
    }

    private fun generateDates(from: Date): Observable<List<Date>> {
        return Observable.fromPublisher {
            val calendar = Calendar.getInstance()
            calendar.time = from
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 1)
            val list = mutableListOf<Date>()
            for (i in 0 until 4) {
                list.add(calendar.time)
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            it.onNext(list)
            it.onComplete()
        }
    }
}