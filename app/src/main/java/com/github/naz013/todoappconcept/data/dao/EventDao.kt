package com.github.naz013.todoappconcept.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.github.naz013.todoappconcept.data.Event

@Dao
interface EventDao : BaseDao<Event> {

    @Query("SELECT * FROM Event WHERE uuId = :uuId")
    fun liveById(uuId: String): LiveData<Event>

    @Query("SELECT * FROM Event")
    fun liveAll(): LiveData<List<Event>>

    @Query("DELETE FROM Event")
    suspend fun deleteAll()
}