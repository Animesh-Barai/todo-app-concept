package com.github.naz013.todoappconcept.data.repository.folder_with_events

import com.github.naz013.todoappconcept.data.FolderWithEvents
import com.github.naz013.todoappconcept.data.dao.FolderWithEventDao
import timber.log.Timber
import javax.inject.Inject

class LocalFolderWithEventsRepository @Inject constructor(private val folderWithEventDao: FolderWithEventDao) :
    FolderWithEventsRepository {
    override fun get(uuId: String) = folderWithEventDao.get(uuId)

    override fun all() = folderWithEventDao.getAll()

    override fun getAllInRange(dtStart: String, dtEnd: String): List<FolderWithEvents> {
        val all = all()
        Timber.d("getAllInRange: all -> $all")
        val filtered = mutableListOf<FolderWithEvents>()
        all.forEach {
            val folder = it
            folder.events = folder.events.filter { event ->
                event.dueDate ?: "" >= dtStart && event.dueDate ?: "" < dtEnd
            }
            if (folder.events.isNotEmpty()) {
                filtered.add(folder)
            }
        }
        Timber.d("getAllInRange: filtered -> $filtered")
        return filtered
    }
}