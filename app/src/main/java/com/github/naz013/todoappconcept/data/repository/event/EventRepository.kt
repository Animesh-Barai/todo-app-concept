package com.github.naz013.todoappconcept.data.repository.event

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Event

interface EventRepository {
    fun get(uuId: String): LiveData<Event>
}