package com.github.naz013.todoappconcept.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.github.naz013.todoappconcept.data.Event
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface EventDao : BaseDao<Event> {

    @Query("SELECT * FROM Event WHERE uuId = :uuId")
    fun liveById(uuId: String): Flowable<Event>

    @Query("SELECT * FROM Event")
    fun liveAll(): Flowable<List<Event>>

    @Query("SELECT * FROM Event WHERE dueDate >= :dtStart AND dueDate < :dtEnd")
    fun getAllInRange(dtStart: String, dtEnd: String): List<Event>

    @Query("DELETE FROM Event")
    fun deleteAll(): Completable
}