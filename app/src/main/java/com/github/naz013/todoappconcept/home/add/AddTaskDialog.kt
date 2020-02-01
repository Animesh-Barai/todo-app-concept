package com.github.naz013.todoappconcept.home.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.github.naz013.todoappconcept.TodoApp
import com.github.naz013.todoappconcept.data.Folder
import com.github.naz013.todoappconcept.databinding.DialogAddTaskBinding
import com.github.naz013.todoappconcept.utils.dp2px
import com.github.naz013.todoappconcept.utils.toUserReadableTime
import com.github.naz013.todoappconcept.utils.toUserScreenDate
import com.github.naz013.todoappconcept.views.SuggestionTextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AddTaskDialog : AddDialogView {

    @Inject
    lateinit var presenter: AddDialogPresenter
    private var dialog: BottomSheetDialog? = null
    private var binding: DialogAddTaskBinding? = null
    private val form = AddTaskForm()

    init {
        TodoApp.appComponent?.inject(this)
    }

    fun showDialog(context: Context, date: Date) {
        val view = DialogAddTaskBinding.inflate(LayoutInflater.from(context))
        view.saveButton.setOnClickListener { presenter.saveTask(createForm(view)) }

        dialog = BottomSheetDialog(context)
        dialog?.setContentView(view.root)
        dialog?.show()

        binding = view
        presenter.setView(this)
        presenter.loadDates()
        presenter.loadFolders()
    }

    private fun createForm(binding: DialogAddTaskBinding): AddTaskForm {
        form.title = binding.titleFieldView.text.toString()
        form.description = binding.detailsFieldView.text.toString()
        return form
    }

    override fun showSelectedDate(date: Date?) {
        form.date = date
        if (date == null) {
            binding?.dateView?.visibility = View.GONE
        } else {
            binding?.dateSuggestionsView?.visibility = View.GONE
            binding?.dateView?.visibility = View.VISIBLE
            binding?.removeDateButton?.setOnClickListener { presenter.removeDate() }
            binding?.dateValueView?.text = date.toUserScreenDate()
        }
    }

    override fun showSelectedFolder(folder: Folder?) {
        form.folderId = folder?.uuId
        if (folder == null) {
            binding?.folderView?.visibility = View.GONE
        } else {
            binding?.folderSuggestionsView?.visibility = View.GONE
            binding?.folderView?.visibility = View.VISIBLE
            binding?.removeFolderButton?.setOnClickListener { presenter.removeFolder() }
            binding?.folderValueView?.text = folder.title
        }
    }

    override fun showSelectedTime(time: Date?) {
        form.time = time
        if (time == null) {
            binding?.timeView?.visibility = View.GONE
            binding?.timeSuggestionsView?.visibility = View.GONE
        } else {
            binding?.timeSuggestionsView?.visibility = View.GONE
            binding?.timeView?.visibility = View.VISIBLE
            binding?.removeTimeButton?.setOnClickListener { presenter.removeTime() }
            binding?.timeValueView?.text = time.toUserReadableTime()
        }
    }

    override fun showFolders(folders: List<Folder>) {
        Timber.d("showFolders: $folders")
        val folderSuggestionView = binding?.folderSuggestionsView ?: return
        if (folders.isEmpty()) {
            folderSuggestionView.visibility = View.GONE
            return
        }

        folderSuggestionView.visibility = View.VISIBLE
        folderSuggestionView.removeAllViewsInLayout()

        val margin = folderSuggestionView.context.dp2px(4)
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(margin, 0, margin, 0)

        folders.forEachIndexed { index, folder ->
            val textView = SuggestionTextView(folderSuggestionView.context)
            textView.id = index
            textView.text = folder.title
            textView.setOnClickListener {
                presenter.pickFolder(folder)
            }
            folderSuggestionView.addView(textView, params)
        }
    }

    override fun showDateSuggestions(dates: List<Date>) {
        Timber.d("showDateSuggestions: $dates")
        val dateSuggestionView = binding?.dateSuggestionsView ?: return

        dateSuggestionView.visibility = View.VISIBLE
        dateSuggestionView.removeAllViewsInLayout()

        val margin = dateSuggestionView.context.dp2px(4)
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(margin, 0, margin, 0)

        dates.forEachIndexed { index, date ->
            val textView = SuggestionTextView(dateSuggestionView.context)
            textView.id = index
            textView.text = date.toUserScreenDate()
            textView.setOnClickListener {
                presenter.pickDate(date)
            }
            dateSuggestionView.addView(textView, params)
        }
    }

    override fun showTimeSuggestions(times: List<Date>) {
        Timber.d("showTimeSuggestions: $times")
        val timeSuggestionView = binding?.timeSuggestionsView ?: return

        timeSuggestionView.visibility = View.VISIBLE
        timeSuggestionView.removeAllViewsInLayout()

        val margin = timeSuggestionView.context.dp2px(4)
        val params: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(margin, 0, margin, 0)

        times.forEachIndexed { index, date ->
            val textView = SuggestionTextView(timeSuggestionView.context)
            textView.id = index
            textView.text = date.toUserReadableTime()
            textView.setOnClickListener {
                presenter.pickTime(date)
            }
            timeSuggestionView.addView(textView, params)
        }
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
        dialog = null
        binding = null
    }
}