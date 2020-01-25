package com.github.naz013.todoappconcept.di

import android.app.Application
import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.data.dao.FolderDao
import com.github.naz013.todoappconcept.data.dao.FolderWithEventDao
import com.github.naz013.todoappconcept.data.repository.event.all.EventsRepository
import com.github.naz013.todoappconcept.data.repository.event.all.LocalEventsRepository
import com.github.naz013.todoappconcept.data.repository.event.EventRepository
import com.github.naz013.todoappconcept.data.repository.event.LocalEventRepository
import com.github.naz013.todoappconcept.data.repository.folder.FolderRepository
import com.github.naz013.todoappconcept.data.repository.folder.LocalFolderRepository
import com.github.naz013.todoappconcept.data.repository.folder.all.FoldersRepository
import com.github.naz013.todoappconcept.data.repository.folder.all.LocalFoldersRepository
import com.github.naz013.todoappconcept.data.repository.folder_with_events.FolderWithEventsRepository
import com.github.naz013.todoappconcept.data.repository.folder_with_events.LocalFolderWithEventsRepository
import com.github.naz013.todoappconcept.utils.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [
        DbModule::class
    ]
)
class AppModule {

    @Singleton
    @Provides
    fun provideFolderWithEventsRepository(folderWithEventDao: FolderWithEventDao): FolderWithEventsRepository {
        return LocalFolderWithEventsRepository(folderWithEventDao)
    }

    @Singleton
    @Provides
    fun provideFoldersRepository(folderDao: FolderDao): FoldersRepository {
        return LocalFoldersRepository(folderDao)
    }

    @Singleton
    @Provides
    fun provideFolderRepository(folderDao: FolderDao): FolderRepository {
        return LocalFolderRepository(folderDao)
    }

    @Singleton
    @Provides
    fun provideEventsRepository(eventDao: EventDao): EventsRepository {
        return LocalEventsRepository(eventDao)
    }

    @Singleton
    @Provides
    fun provideEventRepository(eventDao: EventDao): EventRepository {
        return LocalEventRepository(
            eventDao
        )
    }

    @Singleton
    @Provides
    fun providePrefs(app: Application) = Prefs(app)
}