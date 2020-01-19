package com.github.naz013.todoappconcept.data.repository.folder

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Folder

interface FolderRepository {
    fun get(uuId: String): LiveData<Folder>
}