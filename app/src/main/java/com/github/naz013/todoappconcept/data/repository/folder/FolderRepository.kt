package com.github.naz013.todoappconcept.data.repository.folder

import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.repository.BaseRepository
import io.reactivex.Completable
import io.reactivex.Flowable

interface FolderRepository : BaseRepository<Folder> {
    fun liveById(uuId: String): Flowable<Folder>
    fun liveAll(): Flowable<List<Folder>>
    fun deleteAll(): Completable
}