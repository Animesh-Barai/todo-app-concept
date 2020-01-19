package com.github.naz013.todoappconcept.di

import android.app.Application
import com.github.naz013.todoappconcept.data.db.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideEventDao(appDb: AppDb) = appDb.eventDao()

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDb.getAppDatabase(app)
}