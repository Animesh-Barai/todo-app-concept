package com.github.naz013.todoappconcept.data.converter

import com.github.naz013.todoappconcept.data.EventState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class EventStateConverterTest {

    @Test
    fun toEnum_checkFunctionCall() {
        val eventStateConverter: EventStateConverter = mock()
        eventStateConverter.toEnum(0)
        verify(eventStateConverter).toEnum(0)
    }

    @Test
    fun toInt_checkFunctionCall() {
        val eventStateConverter: EventStateConverter = mock()
        eventStateConverter.toInt(EventState.COMPLETED)
        verify(eventStateConverter).toInt(EventState.COMPLETED)
    }

    @Test
    fun toInt_shouldReturnCorespondentIntValue() {
        val eventStateConverter = EventStateConverter()
        assertEquals(0, eventStateConverter.toInt(EventState.ACTIVE))
        assertEquals(1, eventStateConverter.toInt(EventState.COMPLETED))
        assertEquals(2, eventStateConverter.toInt(EventState.REMOVED))
    }

    @Test
    fun toEnum_shouldReturnCorrespondingEnumValue() {
        val eventStateConverter = EventStateConverter()
        assertEquals(EventState.ACTIVE, eventStateConverter.toEnum(0))
        assertEquals(EventState.COMPLETED, eventStateConverter.toEnum(1))
        assertEquals(EventState.REMOVED, eventStateConverter.toEnum(2))
    }
}