package com.github.naz013.todoappconcept.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.naz013.todoappconcept.data.Event
import com.github.naz013.todoappconcept.data.db.AppDb
import com.github.naz013.todoappconcept.utils.threading.TrampolineSchedulerProvider
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class EventDaoTest {

    private lateinit var eventDao: EventDao
    private lateinit var db: AppDb
    private var schedulerProvider = TrampolineSchedulerProvider()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
        eventDao = db.eventDao()
    }

    @Test
    fun insert() {
        val event = Event()

        val insertObserver = eventDao.insert(event)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .test()

        insertObserver.assertNoErrors()
                .assertComplete()
        insertObserver.dispose()

        val getObserver = eventDao.liveById(event.uuId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .test()

        getObserver.assertNoErrors()
                .assertValueCount(1);
        getObserver.dispose()

//        eventDao.liveById(event.uuId).test()
//            .assertResult(event)
//
//        eventDao.liveAll()
//            .test()
//            .assertResult(listOf(event))
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}