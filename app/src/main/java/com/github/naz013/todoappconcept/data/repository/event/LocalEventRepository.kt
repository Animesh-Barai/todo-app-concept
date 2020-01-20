package com.github.naz013.todoappconcept.data.repository.event

import com.github.naz013.todoappconcept.data.dao.EventDao
import javax.inject.Inject

class LocalEventRepository @Inject constructor(private val eventDao: EventDao) :
    EventRepository {
    override fun get(uuId: String) = eventDao.liveById(uuId)
}