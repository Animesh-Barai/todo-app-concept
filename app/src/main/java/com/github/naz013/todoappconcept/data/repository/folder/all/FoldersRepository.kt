package com.github.naz013.todoappconcept.data.repository.folder.all

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Folder

interface FoldersRepository {
    fun all(): LiveData<List<Folder>>
}