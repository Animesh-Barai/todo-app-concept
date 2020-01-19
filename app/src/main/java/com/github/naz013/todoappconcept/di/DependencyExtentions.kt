package com.github.naz013.todoappconcept.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(
    viewModelStore: ViewModelStore,
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProvider(viewModelStore, factory)[T::class.java]
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    viewModelStore: ViewModelStore,
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProvider(viewModelStore, factory)[T::class.java]
}