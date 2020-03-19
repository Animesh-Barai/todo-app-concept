package com.github.naz013.todoappconcept.di.modules

import com.github.naz013.todoappconcept.data.repository.event.EventRepository
import com.github.naz013.todoappconcept.data.repository.folder.FolderRepository
import com.github.naz013.todoappconcept.data.repository.folder_with_events.FolderWithEventsRepository
import com.github.naz013.todoappconcept.di.AppModule
import com.github.naz013.todoappconcept.home.add.AddDialogPresenter
import com.github.naz013.todoappconcept.home.add.AddDialogPresenterImpl
import com.github.naz013.todoappconcept.home.presenter.HomePresenter
import com.github.naz013.todoappconcept.home.presenter.HomePresenterImpl
import com.github.naz013.todoappconcept.utils.threading.NormalSchedulerProvider
import com.github.naz013.todoappconcept.utils.threading.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [AppModule::class]
)
class PresenterModule {

    @Provides
    fun provideAddDialogPresenter(
        schedulerProvider: SchedulerProvider,
        eventRepository: EventRepository,
        folderRepository: FolderRepository
    ): AddDialogPresenter =
        AddDialogPresenterImpl(schedulerProvider, eventRepository, folderRepository)

    @Provides
    fun provideHomePresenter(
        schedulerProvider: SchedulerProvider,
        folderWithEventsRepository: FolderWithEventsRepository,
        eventRepository: EventRepository
    ): HomePresenter =
        HomePresenterImpl(schedulerProvider, folderWithEventsRepository, eventRepository)

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = NormalSchedulerProvider()
}