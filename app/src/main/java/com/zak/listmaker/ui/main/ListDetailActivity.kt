package com.zak.listmaker.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zak.listmaker.MainActivity.Companion.INTENT_LIST_KEY
import com.zak.listmaker.R
import com.zak.listmaker.models.TaskList
import com.zak.listmaker.ui.main.ui.detail.ListDetailFragment

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        list = intent.getParcelableExtra(INTENT_LIST_KEY) ?: TaskList("EMPTY")
        title = list.name
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }
}