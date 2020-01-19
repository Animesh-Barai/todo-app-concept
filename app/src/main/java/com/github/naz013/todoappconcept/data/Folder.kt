package com.github.naz013.todoappconcept.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Folder(
    @PrimaryKey
    var uuId: String = UUID.randomUUID().toString(),
    var title: String? = ""
)