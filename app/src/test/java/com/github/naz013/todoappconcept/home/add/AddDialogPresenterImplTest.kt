package com.github.naz013.todoappconcept.home.add

import com.github.naz013.todoappconcept.data.repository.folder.FolderRepository
import com.github.naz013.todoappconcept.utils.threading.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import java.util.*

class AddDialogPresenterImplTest {

    private lateinit var view: AddDialogView
    private lateinit var presenter: AddDialogPresenter
    private lateinit var folderRepository: FolderRepository
    private val testSchedulerProvider = TestSchedulerProvider(TestScheduler())

    @Before
    fun before() {
        view = mock()
        folderRepository = mock()
        presenter = AddDialogPresenterImpl(testSchedulerProvider, mock(), folderRepository)
        presenter.setView(view)
    }

    @Test
    fun saveClick_shouldShowError() {
        whenever(view.populateForm(mock()))
            .thenReturn(null)

        presenter.saveClick()

        verify(view)
            .showError("Failed to save Task")
    }

    @Test
    fun saveClick_shouldNotShowError() {
        whenever(view.populateForm(mock()))
            .thenReturn(AddTaskForm(title = "Title", folderId = "sjdaksd"))

        presenter.saveClick()

        verify(view).showMessage(any())
        then(view).should(times(1)).closeDialog()
    }

    @Test
    fun pickDate() {
        val date = Date()
        presenter.pickDate(date)
        verify(view).showSelectedDate(date)
    }

    @Test
    fun removeDate() {
    }

    @Test
    fun pickTime() {
    }

    @Test
    fun removeTime() {
    }

    @Test
    fun pickFolder() {
    }

    @Test
    fun removeFolder() {
    }

    @Test
    fun loadFolders() {
    }

    @Test
    fun loadDates() {
    }

    @Test
    fun loadTimes() {
    }

    @Test
    fun onDestroy() {
    }
}