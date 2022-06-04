package com.example.dayjourney.list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.dayjourney.R
import com.example.dayjourney.data.DiaryEntry

class ListAdapter() :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var userList = emptyList<DiaryEntry>()

    class ListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.entry_item, parent, false ))
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val item = userList[position]
        // Needed to call startActivity
        val context = holder.view.context

        // Set the text of the WordViewHolder
        holder.itemView.findViewById<TextView>(R.id.title_view).text = item.title
        holder.itemView.findViewById<TextView>(R.id.date_view).text = item.date
        holder.itemView.findViewById<TextView>(R.id.entry_view).text = item.text
        when(item.mood){
            1 -> holder.itemView.findViewById<ImageView>(R.id.mood_view).setImageResource(R.drawable.ic_mood_1)
            2 -> holder.itemView.findViewById<ImageView>(R.id.mood_view).setImageResource(R.drawable.ic_mood_2)
            3 -> holder.itemView.findViewById<ImageView>(R.id.mood_view).setImageResource(R.drawable.ic_mood_3)
            4 -> holder.itemView.findViewById<ImageView>(R.id.mood_view).setImageResource(R.drawable.ic_mood_4)
            5 -> holder.itemView.findViewById<ImageView>(R.id.mood_view).setImageResource(R.drawable.ic_mood_5)
            6 -> holder.itemView.findViewById<ImageView>(R.id.mood_view).setImageResource(R.drawable.ic_mood_6)
        }
    }

    fun setData(entry: List<DiaryEntry>){
        this.userList = entry
        notifyDataSetChanged()
    }


    }

