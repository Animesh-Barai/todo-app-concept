package com.github.naz013.todoappconcept.data.repository.event

import com.github.naz013.todoappconcept.data.Event
import io.reactivex.Flowable

interface EventRepository {
    fun get(uuId: String): Flowable<Event>
}