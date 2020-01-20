package com.github.naz013.todoappconcept.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.github.naz013.todoappconcept.data.FolderWithEvents
import io.reactivex.Flowable

@Dao
interface FolderWithEventDao {

    @Transaction
    @Query("SELECT * FROM Folder WHERE uuId = :uuId")
    fun liveById(uuId: String): Flowable<FolderWithEvents>

    @Transaction
    @Query("SELECT * FROM Folder")
    fun liveAll(): Flowable<List<FolderWithEvents>>
}