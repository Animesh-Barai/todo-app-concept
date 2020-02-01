package com.github.naz013.todoappconcept.home.add

import java.util.*

data class AddTaskForm(
    var title: String = "",
    var description: String? = null,
    var folderId: String? = null,
    var date: Date? = null,
    var time: Date? = null
)