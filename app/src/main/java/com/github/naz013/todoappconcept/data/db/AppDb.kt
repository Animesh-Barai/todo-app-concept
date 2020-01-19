package com.github.naz013.todoappconcept.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.data.dao.FolderDao
import com.github.naz013.todoappconcept.data.dao.FolderWithEventDao

@Database(
    entities = [
        Event::class,
        Folder::class
    ],
    version = 1, exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun folderDao(): FolderDao
    abstract fun folderWithEventsDao(): FolderWithEventDao

    companion object {

        private var INSTANCE: AppDb? = null

        fun getAppDatabase(context: Context): AppDb {
            var instance = INSTANCE
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context.applicationContext, AppDb::class.java, "app_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            INSTANCE = instance
            return instance
        }
    }
}