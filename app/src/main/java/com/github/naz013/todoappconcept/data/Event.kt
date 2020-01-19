package com.github.naz013.todoappconcept.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.github.naz013.todoappconcept.data.converter.EventStateConverter
import java.util.*

@Entity
@TypeConverters(
    EventStateConverter::class
)
data class Event(
    @PrimaryKey
    var uuId: String = UUID.randomUUID().toString(),
    var summary: String? = "",
    var description: String? = "",
    var createAt: String? = null,
    var dueDate: String? = null,
    var dueTime: String? = null,
    var state: EventState = EventState.ACTIVE
)

enum class EventState {
    ACTIVE, COMPLETED, REMOVED
}