package com.github.naz013.todoappconcept.arch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.naz013.todoappconcept.di.Injectable
import javax.inject.Inject

abstract class BaseFragment<B : ViewBinding, V : BaseView, P : BasePresenter<V>> : Fragment(), Injectable {

    protected lateinit var binding: B
    @Inject
    lateinit var presenter: P

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        presenter.setView(view())
        return binding.root
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    abstract fun view(): V

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B
}