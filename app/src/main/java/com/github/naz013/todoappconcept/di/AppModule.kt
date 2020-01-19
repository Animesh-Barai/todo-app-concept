package com.github.naz013.todoappconcept.di

import android.app.Application
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
    fun providePrefs(app: Application) = Prefs(app)
}