package com.github.naz013.todoappconcept.data.repository.folder_with_events

import com.github.naz013.todoappconcept.data.FolderWithEvents
import io.reactivex.Flowable

interface FolderWithEventsRepository {
    fun get(uuId: String): Flowable<FolderWithEvents>
}