package com.github.naz013.todoappconcept.data.repository.folder_with_events

import com.github.naz013.todoappconcept.data.FolderWithEvents

interface FolderWithEventsRepository {
    fun get(uuId: String): FolderWithEvents?
    fun getAllInRange(dtStart: String, dtEnd: String): List<FolderWithEvents>
    fun all(): List<FolderWithEvents>
}