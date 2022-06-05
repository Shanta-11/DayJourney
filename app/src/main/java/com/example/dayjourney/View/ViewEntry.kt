package com.example.dayjourney.View

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dayjourney.R
import com.example.dayjourney.data.DiaryEntryViewModel
import com.example.dayjourney.update.UpdateFragmentArgs
import com.google.android.material.button.MaterialButton


class ViewEntry : Fragment() {
    private val args by navArgs<ViewEntryArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_entry, container, false)

        view?.findViewById<TextView>(R.id.textView5)?.setText(args.currentEntry.title)
        view?.findViewById<TextView>(R.id.textView4)?.setText(args.currentEntry.text)
        view?.findViewById<ImageView>(R.id.imageView)?.setImageResource(
            when(args.currentEntry.mood){
                1 -> R.drawable.ic_mood_1
                2 -> R.drawable.ic_mood_2
                3 -> R.drawable.ic_mood_3
                4 -> R.drawable.ic_mood_4
                5 -> R.drawable.ic_mood_5
                else -> R.drawable.ic_mood_6
            }
        )

        view.findViewById<ImageView>(R.id.back_img).setOnClickListener{
            findNavController().navigate(R.id.action_viewEntry_to_entryListFragment)
        }

        view.findViewById<MaterialButton>(R.id.Edit_Entry).setOnClickListener{
            findNavController().navigate(ViewEntryDirections.actionViewEntryToUpdateFragment(args.currentEntry))
        }

        view.findViewById<TextView>(R.id.textView4).setMovementMethod(ScrollingMovementMethod())

        return view
    }


}