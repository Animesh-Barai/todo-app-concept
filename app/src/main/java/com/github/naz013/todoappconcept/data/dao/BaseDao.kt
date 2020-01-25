package com.github.naz013.todoappconcept.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<T>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T): Completable

    @Delete
    fun delete(t: T): Completable
}