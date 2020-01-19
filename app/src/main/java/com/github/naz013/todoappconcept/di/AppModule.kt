package com.github.naz013.todoappconcept.di

import android.app.Application
import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.data.repository.all.EventsRepository
import com.github.naz013.todoappconcept.data.repository.all.LocalEventsRepository
import com.github.naz013.todoappconcept.data.repository.single.EventRepository
import com.github.naz013.todoappconcept.data.repository.single.LocalEventRepository
import com.github.naz013.todoappconcept.utils.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    ViewModelModule::class,
    DbModule::class
])
class AppModule {

    @Singleton
    @Provides
    fun provideEventsRepository(eventDao: EventDao): EventsRepository {
        return LocalEventsRepository(eventDao)
    }

    @Singleton
    @Provides
    fun provideEventRepository(eventDao: EventDao): EventRepository {
        return LocalEventRepository(eventDao)
    }

    @Singleton
    @Provides
    fun providePrefs(app: Application) = Prefs(app)
}