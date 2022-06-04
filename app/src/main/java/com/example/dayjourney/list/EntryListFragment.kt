package com.example.dayjourney.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dayjourney.R
import com.example.dayjourney.data.DiaryEntryViewModel
import com.example.dayjourney.databinding.FragmentEntryListBinding
import com.google.android.material.button.MaterialButton




class EntryListFragment : Fragment() {


    private var _binding: FragmentEntryListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDiaryEntryViewModel: DiaryEntryViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntryListBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = ListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mDiaryEntryViewModel = ViewModelProvider(this).get(DiaryEntryViewModel::class.java)
        mDiaryEntryViewModel.readAllEntries.observe(viewLifecycleOwner , Observer { entry ->
            adapter.setData(entry)

        })

        view.findViewById<MaterialButton>(R.id.newEntry).setOnClickListener{
            findNavController().navigate(R.id.action_entryListFragment_to_newEntryFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}