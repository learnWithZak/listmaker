package com.zak.listmaker.ui.main.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zak.listmaker.MainActivity
import com.zak.listmaker.databinding.FragmentListDetailBinding
import com.zak.listmaker.models.TaskList
import com.zak.listmaker.ui.main.MainViewModel
import com.zak.listmaker.ui.main.MainViewModelFactory

class ListDetailFragment : Fragment() {

    lateinit var binding: FragmentListDetailBinding

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity()))
        )
            .get(MainViewModel::class.java)

        val list: TaskList? = arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)
        if (list != null) {
            viewModel.list = list
            requireActivity().title = list.name
        }
        binding.listItemsRecyclerview.adapter = ListItemsRecyclerViewAdapter(viewModel.list)
        binding.listItemsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.onTaskAdded = {
            val adapter = binding.listItemsRecyclerview.adapter as ListItemsRecyclerViewAdapter
            adapter.tasksUpdated()
        }
    }
}