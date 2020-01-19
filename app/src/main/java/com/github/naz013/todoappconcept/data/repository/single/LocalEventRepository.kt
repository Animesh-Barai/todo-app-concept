package com.github.naz013.todoappconcept.data.repository.single

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.dao.EventDao
import javax.inject.Inject

class LocalEventRepository @Inject constructor(private val eventDao: EventDao) : EventRepository {
    override fun get(uuId: String): LiveData<Event> {
        return eventDao.liveById(uuId)
    }
}