package com.kabos.firebasesample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kabos.firebasesample.MemoAdapter
import com.kabos.firebasesample.R
import com.kabos.firebasesample.databinding.FragmentMainBinding
import com.kabos.firebasesample.model.MemoItem
import com.kabos.firebasesample.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MyViewModel by activityViewModels()
    private val memoAdapter by lazy { MemoAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rvMain.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = memoAdapter
                addItemDecoration(DividerItemDecoration(activity,LinearLayoutManager(activity).orientation))
            }

            btnAddMemo.setOnClickListener {
                findNavController().navigate(R.id.action_nav_main_to_nav_dialog)
            }
            btnGetMemo.setOnClickListener {
                viewModel.getMemo()
            }

            viewModel.memoList.observe(viewLifecycleOwner,{list ->
                memoAdapter.submitList(list)
            })
        }


    }
}
