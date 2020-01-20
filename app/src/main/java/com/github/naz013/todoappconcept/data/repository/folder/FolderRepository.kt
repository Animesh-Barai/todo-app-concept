package com.github.naz013.todoappconcept.data.repository.folder

import com.github.naz013.todoappconcept.data.Folder
import io.reactivex.Flowable

interface FolderRepository {
    fun get(uuId: String): Flowable<Folder>
}