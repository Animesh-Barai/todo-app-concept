package com.github.naz013.todoappconcept.data.converters

import androidx.room.TypeConverter
import com.github.naz013.todoappconcept.data.EventState

class EventStateConverter {

    @TypeConverter
    fun toInt(eventState: EventState) = when (eventState) {
        EventState.ACTIVE -> 0
        EventState.COMPLETED -> 1
        EventState.REMOVED -> 2
    }

    @TypeConverter fun toEnum(state: Int) = when (state) {
        0 -> EventState.ACTIVE
        1 -> EventState.COMPLETED
        2 -> EventState.REMOVED
        else -> EventState.ACTIVE
    }
}