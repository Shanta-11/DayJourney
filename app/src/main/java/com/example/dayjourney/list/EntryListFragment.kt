package com.example.dayjourney.list

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dayjourney.R
import com.example.dayjourney.add.MoodAdapter
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
    ): View {
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


        view.findViewById<ImageView>(R.id.deleteAll).setOnClickListener{
            deleteAllUI()
        }

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

    private fun deleteAllUI(){
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes"){_,_ ->

            builder.setPositiveButton("Yes"){_,_ ->
                mDiaryEntryViewModel.deleteAllUsers()
                Toast.makeText(requireContext(), "Poof!, Wiped all Thoughts!!", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("No"){_,_ ->
                Toast.makeText(requireContext(), "Phew, You scared me...", Toast.LENGTH_SHORT).show()
            }
            builder.setTitle("Delete ALL Thought?")
            builder.setMessage("One Last time, Should I delete ALL of your Thoughts??")
            builder.create().show()
        }

        builder.setNegativeButton("No"){_,_ ->
            Toast.makeText(requireContext(), "Good, No Thoughts deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle("Delete ALL Thoughts?")
        builder.setMessage("I'll wipe all your thoughts, Are you ok with that?")
        builder.create().show()
    }



}