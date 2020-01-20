package com.github.naz013.todoappconcept.data.repository.folder

import com.github.naz013.todoappconcept.data.dao.FolderDao
import javax.inject.Inject

class LocalFolderRepository @Inject constructor(private val folderDao: FolderDao) :
    FolderRepository {
    override fun get(uuId: String) = folderDao.liveById(uuId)
}