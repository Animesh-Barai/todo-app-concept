package com.github.naz013.todoappconcept.data

import androidx.room.Embedded
import androidx.room.Relation

data class FolderWithEvents(
    @Embedded
    var folder: Folder? = null,
    @Relation(
        entity = Event::class,
        parentColumn = "uuId",
        entityColumn = "folderId"
    )
    var events: List<Event> = listOf()
)