package com.github.naz013.todoappconcept.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.naz013.todoappconcept.data.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM Event WHERE uuId = :uuId")
    fun liveById(uuId: String): LiveData<Event>

    @Query("SELECT * FROM Event")
    fun loadAll(): LiveData<List<Event>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<Event>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    @Delete
    suspend fun delete(event: Event)

    @Query("DELETE FROM Event")
    suspend fun deleteAll()
}