package com.github.naz013.todoappconcept.data.repository.folder

import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.dao.FolderDao

class LocalFolderRepository constructor(private val folderDao: FolderDao) : FolderRepository {
    override fun liveById(uuId: String) = folderDao.liveById(uuId)

    override fun liveAll() = folderDao.liveAll()

    override fun deleteAll() = folderDao.deleteAll()

    override fun insertAll(data: List<Folder>) = folderDao.insertAll(data)

    override fun insert(t: Folder) = folderDao.insert(t)

    override fun delete(t: Folder) = folderDao.delete(t)
}