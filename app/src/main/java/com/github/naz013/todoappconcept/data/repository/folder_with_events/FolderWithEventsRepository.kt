package com.github.naz013.todoappconcept.data.repository.folder_with_events

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.FolderWithEvents

interface FolderWithEventsRepository {
    fun get(uuId: String): LiveData<FolderWithEvents>
}