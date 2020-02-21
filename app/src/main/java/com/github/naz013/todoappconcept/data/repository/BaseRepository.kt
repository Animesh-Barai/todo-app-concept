package com.github.naz013.todoappconcept.data.repository

import io.reactivex.Completable

interface BaseRepository<T> {
    fun insertAll(data: List<T>): Completable
    fun insert(t: T): Completable
    fun delete(t: T): Completable
}