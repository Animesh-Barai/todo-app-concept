package com.github.naz013.todoappconcept.data.repository.all

import androidx.lifecycle.LiveData
import com.github.naz013.todoappconcept.data.Event

interface EventsRepository {
    fun all(): LiveData<List<Event>>
}