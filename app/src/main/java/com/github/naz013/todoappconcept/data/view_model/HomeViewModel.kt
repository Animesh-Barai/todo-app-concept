package com.github.naz013.todoappconcept.data.view_model

import androidx.lifecycle.ViewModel
import com.github.naz013.todoappconcept.data.repository.all.EventsRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val eventsRepository: EventsRepository) :
    ViewModel() {


}