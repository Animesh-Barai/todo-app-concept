package com.github.naz013.todoappconcept.home.add

import java.util.*

data class AddTaskForm(
    val title: String,
    val description: String? = null,
    val folderId: String? = null,
    val date: Date? = null,
    val time: Date? = null
)