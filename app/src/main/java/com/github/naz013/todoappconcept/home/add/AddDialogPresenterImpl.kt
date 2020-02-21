package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.EventState
import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.repository.event.EventRepository
import com.github.naz013.todoappconcept.data.repository.folder.FolderRepository
import com.github.naz013.todoappconcept.utils.threading.SchedulerProvider
import com.github.naz013.todoappconcept.utils.toServerTime
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class AddDialogPresenterImpl @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val eventRepository: EventRepository,
    private val folderRepository: FolderRepository
) : AddDialogPresenter {

    private var view: AddDialogView? = null
    private var disposable: Disposable? = null
    private val form = AddTaskForm()

    override fun saveClick() {
        val form = view?.populateForm(form)
        if (form != null) {
            saveTask(form)
        } else {
            view?.showError("Failed to save Task")
        }
    }

    override fun pickDate(date: Date) {
        view?.showSelectedDate(date)
        form.date = date
        loadTimes()
    }

    override fun removeDate() {
        view?.showSelectedDate(null)
        view?.showSelectedTime(null)
        form.date = null
        loadDates()
    }

    override fun pickTime(date: Date) {
        view?.showSelectedTime(date)
        form.time = date
    }

    override fun removeTime() {
        view?.showSelectedTime(null)
        form.time = null
        loadTimes()
    }

    override fun pickFolder(folder: Folder) {
        view?.showSelectedFolder(folder)
        form.folderId = folder.uuId
    }

    override fun removeFolder() {
        view?.showSelectedFolder(null)
        form.folderId = null
        loadFolders()
    }

    override fun loadFolders() {
        disposable = folderRepository.liveAll()
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

    private fun saveTask(addTaskForm: AddTaskForm) {
        if (!verifyForm(addTaskForm)) {
            return
        }
        disposable = Observable.just(addTaskForm)
                .map {
                    if (form.folderId.isNullOrEmpty()) {
                        val folders = folderRepository.liveAll()
                                .blockingFirst()
                        val folder = if (folders.isEmpty()) {
                            defaultFolder().also {
                                folderRepository.insert(it).blockingAwait()
                            }
                        } else {
                            folders.first()
                        }
                        form.folderId = folder.uuId
                    }
                    eventRepository.insert(toEvent(form)).blockingAwait()
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    view?.showMessage("Event saved")
                    view?.closeDialog()
                }, {
                    view?.showError(it.message ?: "")
                })
    }

    private fun defaultFolder(): Folder {
        return Folder().also {
            it.title = "Default"
        }
    }

    private fun toEvent(addTaskForm: AddTaskForm): Event {
        return Event().apply {
            this.createAt = Date().toServerTime()
            this.description = addTaskForm.description
            this.dueDate = addTaskForm.date?.toServerTime()
            this.dueTime = addTaskForm.time?.toServerTime()
            this.folderId = addTaskForm.folderId
            this.state = EventState.ACTIVE
            this.summary = addTaskForm.title
        }
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