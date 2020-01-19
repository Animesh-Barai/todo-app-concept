package com.github.naz013.todoappconcept.data.repository.folder_with_events

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.FolderWithEvents
import com.github.naz013.todoappconcept.data.dao.FolderDao
import com.github.naz013.todoappconcept.data.dao.FolderWithEventDao
import javax.inject.Inject

class LocalFolderWithEventsRepository @Inject constructor(private val folderWithEventDao: FolderWithEventDao) :
    FolderWithEventsRepository {
    override fun get(uuId: String): LiveData<FolderWithEvents> {
        return folderWithEventDao.liveById(uuId)
    }
}