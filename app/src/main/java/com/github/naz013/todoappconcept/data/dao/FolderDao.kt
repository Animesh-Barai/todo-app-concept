package com.github.naz013.todoappconcept.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.github.naz013.todoappconcept.data.Folder

@Dao
interface FolderDao : BaseDao<Folder> {

    @Query("SELECT * FROM Folder WHERE uuId = :uuId")
    fun liveById(uuId: String): LiveData<Folder>

    @Query("SELECT * FROM Event")
    fun liveAll(): LiveData<List<Folder>>

    @Query("DELETE FROM Folder")
    suspend fun deleteAll()
}