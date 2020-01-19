package com.github.naz013.todoappconcept.data.repository.folder.all

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.dao.FolderDao
import javax.inject.Inject

class LocalFoldersRepository @Inject constructor(private val folderDao: FolderDao) : FoldersRepository {
    override fun all(): LiveData<List<Folder>> = folderDao.liveAll()
}