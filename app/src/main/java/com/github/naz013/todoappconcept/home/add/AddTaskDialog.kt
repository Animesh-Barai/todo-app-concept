package com.github.naz013.todoappconcept.home.add

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.github.naz013.todoappconcept.TodoApp
import com.github.naz013.todoappconcept.databinding.DialogAddTaskBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import javax.inject.Inject

class AddTaskDialog : AddDialogView {

    @Inject
    lateinit var presenter: AddDialogPresenter
    private var dialog: BottomSheetDialog? = null

    init {
        TodoApp.appComponent?.inject(this)
    }

    fun showDialog(context: Context) {
        presenter.setView(this)

        val view = DialogAddTaskBinding.inflate(LayoutInflater.from(context))
        view.saveButton.setOnClickListener { presenter.saveTask(createForm(view)) }

        dialog = BottomSheetDialog(context)
        dialog?.setContentView(view.root)
        dialog?.show()
    }

    private fun createForm(binding: DialogAddTaskBinding): AddTaskForm {
        val title = binding.titleFieldView.text.toString()
        val description = binding.detailsFieldView.text.toString()
        return AddTaskForm(
            title = title,
            description = description
        )
    }

    override fun showError(message: String) {
        dialog?.context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showMessage(message: String) {
        dialog?.context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun closeDialog() {
        presenter.onDestroy()
        dialog?.dismiss()
    }
}