package com.zak.listmaker.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.zak.listmaker.MainActivity
import com.zak.listmaker.MainActivity.Companion.INTENT_LIST_KEY
import com.zak.listmaker.R
import com.zak.listmaker.databinding.ActivityListDetailBinding
import com.zak.listmaker.models.TaskList
import com.zak.listmaker.ui.main.ui.detail.ListDetailFragment

class ListDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailBinding
    lateinit var viewModel: MainViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        ).get(MainViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(INTENT_LIST_KEY) ?: TaskList("EMPTY")
        title = viewModel.list.name
        fragment = ListDetailFragment.newInstance()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, fragment)
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(INTENT_LIST_KEY, viewModel.list)

        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }
}