package com.github.naz013.todoappconcept.home.presenter

import com.github.naz013.todoappconcept.R
import com.github.naz013.todoappconcept.data.DateRange
import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.FolderWithEvents
import com.github.naz013.todoappconcept.data.repository.event.EventRepository
import com.github.naz013.todoappconcept.data.repository.folder_with_events.FolderWithEventsRepository
import com.github.naz013.todoappconcept.home.view.HomeView
import com.github.naz013.todoappconcept.utils.TimeUtil
import com.github.naz013.todoappconcept.utils.threading.SchedulerProvider
import com.github.naz013.todoappconcept.utils.toMonth
import com.github.naz013.todoappconcept.utils.toServerTime
import com.github.naz013.todoappconcept.views.DateSelectorView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val eventsWithEventsRepository: FolderWithEventsRepository,
    private val eventRepository: EventRepository
) : HomePresenter {

    private var view: HomeView? = null
    private var disposable: Disposable? = null
    private var dateRange: DateRange? = null

    override fun setView(v: HomeView) {
        view = v
    }

    override fun addButtonClick() {
        dateRange.let {
            if (it == null) {
                view?.openAddDialog(Date())
            } else {
                view?.openAddDialog(it.from)
            }
        }
    }

    override fun loadDates() {
        disposable = generateDates(Date())
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ view?.showDates(it) }, { view?.showError(it.message ?: "") })
    }

    override fun loadEvents(dateRange: DateRange) {
        this.dateRange = dateRange
        disposable = findEvents(dateRange)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ view?.showEvents(it) }, { view?.showError(it.message ?: "") })
    }

    override fun reloadEvents() {
        dateRange?.let { loadEvents(it) }
    }

    override fun onDestroy() {
        disposable?.dispose()
        view = null
    }

    private fun findEvents(dateRange: DateRange): Observable<List<FolderWithEvents>> {
        return Observable.fromPublisher {
            it.onNext(
                eventsWithEventsRepository.getAllInRange(
                    dateRange.from.toServerTime(),
                    dateRange.to.toServerTime()
                )
            )
            it.onComplete()
        }
    }

    private fun generateDates(from: Date): Observable<List<DateSelectorView.DateItem<DateRange>>> {
        return Observable.fromPublisher {
            val calendar = Calendar.getInstance()
            calendar.time = from
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 1)
            val list = mutableListOf<DateSelectorView.DateItem<DateRange>>()
            for (i in 0 until 4) {
                list.add(addEventsForDay(calendar, i == 0))
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
            list.add(addRestEventsForCurrentMonth(calendar))
            it.onNext(list)
            it.onComplete()
        }
    }

    private fun addEventsForDay(
        calendar: Calendar,
        isFirst: Boolean
    ): DateSelectorView.DateItem<DateRange> {
        val dateRange = dateRange(calendar.time, TimeUtil.DAY - TimeUtil.SECOND * 2)
        val items = getEventsForDate(dateRange)
        return DateSelectorView.DateItem(
            day = calendar.get(Calendar.DAY_OF_MONTH),
            count = items.size,
            payload = dateRange,
            subtitle = calendar.time.toMonth(),
            isSelected = isFirst,
            extraText = if (isFirst) "today" else ""
        )
    }

    private fun addRestEventsForCurrentMonth(
        calendar: Calendar
    ): DateSelectorView.DateItem<DateRange> {
        val lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val daysLeft = if (lastDay == calendar.get(Calendar.DAY_OF_MONTH)) {
            1
        } else {
            lastDay - calendar.get(Calendar.DAY_OF_MONTH)
        }

        val dateRange = dateRange(calendar.time, (daysLeft * TimeUtil.DAY) - (TimeUtil.SECOND * 2))
        val items = getEventsForDate(dateRange)

        return DateSelectorView.DateItem(
            day = -1,
            count = items.size,
            payload = dateRange,
            subtitle = "",
            isSelected = false,
            extraText = "",
            icon = R.drawable.ic_calendar_waiting
        )
    }

    private fun dateRange(from: Date, millis: Long): DateRange {
        return DateRange(
            from,
            Date(from.time + millis)
        )
    }

    private fun getEventsForDate(dateRange: DateRange): List<Event> {
        return eventRepository.getAllInRange(
            dateRange.from.toServerTime(),
            dateRange.to.toServerTime()
        )
    }
}