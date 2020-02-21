package com.github.naz013.todoappconcept.data.repository.event

import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.data.repository.BaseRepository
import io.reactivex.Completable
import io.reactivex.Flowable

interface EventRepository : BaseRepository<Event> {
    fun liveById(uuId: String): Flowable<Event>
    fun liveAll(): Flowable<List<Event>>
    fun getAllInRange(dtStart: String, dtEnd: String): List<Event>
    fun deleteAll(): Completable
}