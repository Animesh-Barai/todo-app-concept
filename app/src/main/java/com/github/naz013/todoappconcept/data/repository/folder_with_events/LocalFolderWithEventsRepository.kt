package com.github.naz013.todoappconcept.data.repository.folder_with_events

import com.github.naz013.todoappconcept.data.dao.FolderWithEventDao
import javax.inject.Inject

class LocalFolderWithEventsRepository @Inject constructor(private val folderWithEventDao: FolderWithEventDao) :
    FolderWithEventsRepository {
    override fun get(uuId: String) = folderWithEventDao.liveById(uuId)
}