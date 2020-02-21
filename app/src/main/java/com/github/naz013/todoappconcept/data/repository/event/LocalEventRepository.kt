package com.github.naz013.todoappconcept.data.repository.event

import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.dao.EventDao

class LocalEventRepository(private val eventDao: EventDao) : EventRepository {
    override fun liveById(uuId: String) = eventDao.liveById(uuId)

    override fun liveAll() = eventDao.liveAll()

    override fun getAllInRange(dtStart: String, dtEnd: String) = eventDao.getAllInRange(dtStart, dtEnd)

    override fun deleteAll() = eventDao.deleteAll()

    override fun insertAll(data: List<Event>) = eventDao.insertAll(data)

    override fun insert(t: Event) = eventDao.insert(t)

    override fun delete(t: Event) = eventDao.delete(t)
}