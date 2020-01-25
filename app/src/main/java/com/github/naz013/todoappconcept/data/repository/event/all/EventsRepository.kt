package com.github.naz013.todoappconcept.data.repository.event.all

import com.github.naz013.todoappconcept.data.Event
import io.reactivex.Flowable

interface EventsRepository {
    fun all(): Flowable<List<Event>>
    fun allInRange()
}