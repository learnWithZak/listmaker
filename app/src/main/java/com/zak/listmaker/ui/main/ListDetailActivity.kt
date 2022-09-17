package com.zak.listmaker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.zak.listmaker.MainActivity
import com.zak.listmaker.MainActivity.Companion.INTENT_LIST_KEY
import com.zak.listmaker.R
import com.zak.listmaker.databinding.ActivityListDetailBinding
import com.zak.listmaker.models.TaskList
import com.zak.listmaker.ui.main.ui.detail.ListDetailFragment
import com.zak.listmaker.ui.main.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityListDetailBinding
    lateinit var viewModel: ListDetailViewModel
    lateinit var fragment: ListDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(INTENT_LIST_KEY) ?: TaskList("EMPTY")
        title = viewModel.list.name
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}