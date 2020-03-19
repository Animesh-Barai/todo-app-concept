package com.github.naz013.todoappconcept.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.github.naz013.todoappconcept.data.FolderWithEvents

@Dao
interface FolderWithEventDao {

    @Transaction
    @Query("SELECT * FROM Folder WHERE uuId = :uuId")
    fun get(uuId: String): FolderWithEvents?

    @Transaction
    @Query("SELECT * FROM Folder")
    fun getAll(): List<FolderWithEvents>
}