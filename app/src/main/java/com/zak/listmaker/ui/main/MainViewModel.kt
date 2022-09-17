package com.zak.listmaker.ui.main

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.zak.listmaker.models.TaskList

class MainViewModel(private val sharedPreferences: SharedPreferences) : ViewModel() {
    private val TAG = MainViewModel::class.java.simpleName
    lateinit var onListAdded: (() -> Unit)

    val lists: MutableList<TaskList> by lazy {
        retrieveLists()
    }

    private fun retrieveLists(): MutableList<TaskList> {
        val taskLists = ArrayList<TaskList>()
        val sharedPreferencesContents = sharedPreferences.all

        for (taskList in sharedPreferencesContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            for (element in itemsHashSet) {
                Log.d(TAG, "element of the sharedPref: $element")
            }
            val list = TaskList(taskList.key, itemsHashSet)
            taskLists.add(list)
        }
        return taskLists
    }

    fun saveList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
        onListAdded.invoke()
    }

    fun updateList(list: TaskList) {
        sharedPreferences.edit().putStringSet(list.name, list.tasks.toHashSet()).apply()
        lists.add(list)
    }

    fun refreshLists() {
        lists.clear()
        lists.addAll(retrieveLists())
    }
}