package com.github.naz013.todoappconcept.data.repository.event.all

import com.github.naz013.todoappconcept.data.dao.EventDao
import javax.inject.Inject

class LocalEventsRepository @Inject constructor(private val eventDao: EventDao) : EventsRepository {
    override fun all() = eventDao.liveAll()
}