package com.github.naz013.todoappconcept.data.repository.folder.all

import com.github.naz013.todoappconcept.data.Folder
import io.reactivex.Flowable

interface FoldersRepository {
    fun all(): Flowable<List<Folder>>
}