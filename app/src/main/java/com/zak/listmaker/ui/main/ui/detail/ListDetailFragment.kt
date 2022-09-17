package com.zak.listmaker.ui.main.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zak.listmaker.databinding.FragmentListDetailBinding
import com.zak.listmaker.models.TaskList

class ListDetailFragment : Fragment() {

    lateinit var binding: FragmentListDetailBinding

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var viewModel: ListDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDetailBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = ListItemsRecyclerViewAdapter(arrayListOf(), TaskList(""))
        binding.listsRecyclerview.adapter = recyclerViewAdapter
        return binding.root
    }

}