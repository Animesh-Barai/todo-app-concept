package com.github.naz013.todoappconcept.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.naz013.todoappconcept.arch.BaseFragment
import com.github.naz013.todoappconcept.data.DateRange
import com.github.naz013.todoappconcept.data.FolderWithEvents
import com.github.naz013.todoappconcept.databinding.FragmentHomeBinding
import com.github.naz013.todoappconcept.home.add.AddTaskDialog
import com.github.naz013.todoappconcept.home.presenter.HomePresenter
import com.github.naz013.todoappconcept.home.view.HomeView
import com.github.naz013.todoappconcept.views.DateSelectorView

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeView, HomePresenter>(), HomeView {

    private val addTaskDialog = AddTaskDialog()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener { openAddDialog() }
        presenter.loadDates()
    }

    private fun openAddDialog() {
        addTaskDialog.showDialog(context!!)
    }

    override fun view(): HomeView = this

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun showDates(dates: List<DateSelectorView.DateItem<DateRange>>) {
        binding.dateSelectorView.showDates(dates)
    }

    override fun showEvents(events: List<FolderWithEvents>) {

    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}