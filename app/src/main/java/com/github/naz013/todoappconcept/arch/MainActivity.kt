package com.github.naz013.todoappconcept.arch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.naz013.todoappconcept.R
import com.github.naz013.todoappconcept.databinding.ActivityMainBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
