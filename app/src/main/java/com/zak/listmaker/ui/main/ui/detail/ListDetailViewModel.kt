package com.zak.listmaker.ui.main.ui.detail

import androidx.lifecycle.ViewModel
import com.zak.listmaker.models.TaskList

class ListDetailViewModel : ViewModel() {

    lateinit var onTaskAdded: (() -> Unit) // informs the Fragment when a new task is available
    lateinit var list: TaskList

    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}