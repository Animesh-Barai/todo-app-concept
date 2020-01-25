package com.github.naz013.todoappconcept.di

import android.app.Application
import com.github.naz013.todoappconcept.TodoApp
import com.github.naz013.todoappconcept.di.modules.PresenterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        PresenterModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: TodoApp)
}