package com.github.naz013.todoappconcept.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class TimeUtilKtTest {

    @Test
    fun toUserReadableDate() {
        val date = GregorianCalendar(2020, 1, 5).time
        assertEquals("02.05.2020", TimeUtil.userReadableDate.format(date))
    }

    @Test
    fun toUserReadableTime() {
        val date = GregorianCalendar(2020, 1, 5, 11, 20).time
        assertEquals("11:20", TimeUtil.userReadableTime.format(date))
    }
}