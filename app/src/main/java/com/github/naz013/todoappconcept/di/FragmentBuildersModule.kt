package com.github.naz013.todoappconcept.di

import com.github.naz013.todoappconcept.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}