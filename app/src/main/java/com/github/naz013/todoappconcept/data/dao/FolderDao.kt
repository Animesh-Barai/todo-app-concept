package com.github.naz013.todoappconcept.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.naz013.todoappconcept.data.Folder
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface FolderDao : BaseDao<Folder> {

    @Query("SELECT * FROM Folder WHERE uuId = :uuId")
    fun liveById(uuId: String): Flowable<Folder>

    @Query("SELECT * FROM Folder")
    fun liveAll(): Flowable<List<Folder>>

    @Query("DELETE FROM Folder")
    fun deleteAll(): Completable
}