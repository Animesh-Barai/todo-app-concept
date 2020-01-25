package com.github.naz013.todoappconcept.di.modules

import com.github.naz013.todoappconcept.data.dao.EventDao
import com.github.naz013.todoappconcept.di.AppModule
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
    fun provideHomePresenter(
        schedulerProvider: SchedulerProvider,
        eventDao: EventDao
    ): HomePresenter = HomePresenterImpl(schedulerProvider, eventDao)

    @Singleton
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = NormalSchedulerProvider()
}