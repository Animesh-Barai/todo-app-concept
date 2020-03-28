package com.github.naz013.todoappconcept.data

data class ListEvent(val event: Event, val count: Int, val groupName: String) {

    companion object {
        const val NO_HEADER_COUNTER = -1
    }
}