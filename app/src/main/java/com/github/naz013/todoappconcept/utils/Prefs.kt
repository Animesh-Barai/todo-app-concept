package com.github.naz013.todoappconcept.utils

import android.content.Context

class Prefs(context: Context) {

    private val shared = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {

    }
}