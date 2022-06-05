package com.example.dayjourney.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dayjourney.R
import com.example.dayjourney.add.moodId
import com.example.dayjourney.data.DiaryEntry
import com.example.dayjourney.data.DiaryEntryViewModel
import com.google.android.material.button.MaterialButton


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mDiaryEntryViewModel: DiaryEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mDiaryEntryViewModel = ViewModelProvider(this).get(DiaryEntryViewModel::class.java)
        view.findViewById<EditText>(R.id.Update_title).setText(args.currentEntry.title)
        view.findViewById<EditText>(R.id.UpdatetextField).setText(args.currentEntry.text)

        view.findViewById<ImageView>(R.id.back_img1).setOnClickListener{
            findNavController().navigate(UpdateFragmentDirections.actionUpdateFragmentToViewEntry(args.currentEntry))
        }


        view.findViewById<ImageView>(R.id.delete_Entry).setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())

            builder.setPositiveButton("Yes"){_,_ ->
                mDiaryEntryViewModel.deleteEntry(args.currentEntry)
                Toast.makeText(requireContext(), "Poof!, Deleted!!", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_entryListFragment)
            }

            builder.setNegativeButton("No"){_,_ ->
                Toast.makeText(requireContext(), "Good Decision, Thoughts are precious", Toast.LENGTH_SHORT).show()
            }
            builder.setTitle("Delete Thought?")
            builder.setMessage("I'll delete this thought, Are you ok with that?")
            builder.create().show()


        }


        view.findViewById<MaterialButton>(R.id.Edit_Entry).setOnClickListener{
            updateDataToDatabase()
        }
        return view
    }

    private fun updateDataToDatabase() {
        val entry = view?.findViewById<EditText>(R.id.UpdatetextField)?.text.toString()
        val title = view?.findViewById<EditText>(R.id.Update_title)?.text.toString()

        if (inputCheck(entry, title)) {
            val diaryEntry =
                DiaryEntry(args.currentEntry.id, entry, args.currentEntry.date, title, args.currentEntry.mood)
            mDiaryEntryViewModel.updateEntry(diaryEntry)
            Toast.makeText(requireContext(), "Updated!", Toast.LENGTH_SHORT).show()
            moodId = 0
            findNavController().navigate(R.id.action_updateFragment_to_entryListFragment)
        } else {
            Toast.makeText(requireContext(), "Did you Forget something?", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(entry: String, title: String): Boolean{
        return !(TextUtils.isEmpty(entry) && TextUtils.isEmpty(title))


    }


}