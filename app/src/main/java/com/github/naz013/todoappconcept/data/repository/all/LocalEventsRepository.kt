package com.github.naz013.todoappconcept.data.repository.all

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.dao.EventDao
import javax.inject.Inject

class LocalEventsRepository @Inject constructor(private val eventDao: EventDao) : EventsRepository {
    override fun all(): LiveData<List<Event>> = eventDao.liveAll()
}