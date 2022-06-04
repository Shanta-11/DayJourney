package com.example.dayjourney.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.dayjourney.R
import com.example.dayjourney.data.DiaryEntry
import com.example.dayjourney.data.DiaryEntryViewModel
import com.google.android.material.button.MaterialButton
import java.time.LocalDate
import java.util.*

class NewEntryFragment : Fragment() {

    private lateinit var mDiaryEntryViewModel: DiaryEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_entry, container, false)

        mDiaryEntryViewModel = ViewModelProvider(this).get(DiaryEntryViewModel::class.java)


        view.findViewById<MaterialButton>(R.id.saveEntry).setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val entry = view?.findViewById<EditText>(R.id.textField)?.text.toString()
        val title = view?.findViewById<EditText>(R.id.edit_title)?.text.toString()
        val date = getDate()
        if(inputCheck(entry)){
            val diaryEntry = DiaryEntry(0, entry, date, title, 1)
            mDiaryEntryViewModel.addEntry(diaryEntry)
            Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_newEntryFragment_to_entryListFragment)
        }
        else{
            Toast.makeText(requireContext(), "Did you Forget something?", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDate(): String {

        val day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
        val month = (Calendar.getInstance().get(Calendar.MONTH)+1).toString()
        val year = Calendar.getInstance().get(Calendar.YEAR).toString()
        return day + "/" + month + "/" + year
    }

    private fun inputCheck(entry: String): Boolean{
        return !(TextUtils.isEmpty(entry))

    }


}