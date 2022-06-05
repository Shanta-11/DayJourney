package com.example.dayjourney.add

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dayjourney.R
import com.example.dayjourney.data.DataSource.moodImgs
import com.example.dayjourney.data.MoodImg
import com.google.android.material.card.MaterialCardView
import kotlinx.coroutines.NonDisposableHandle.parent

class MoodAdapter(): RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {

    val moodData: List<MoodImg> = moodImgs
    var moodSelected = -1

    class MoodViewHolder(val view: View): RecyclerView.ViewHolder(view){

    }

    override fun getItemCount(): Int {
        return moodData.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        return MoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mood_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
       val item = moodData[position]

       holder.itemView.findViewById<ImageView>(R.id.MoodView).setImageResource(item.imageResourceId)
       if(position==moodSelected){
           holder.itemView.findViewById<MaterialCardView>(R.id.mood_card).setCardBackgroundColor(Color.rgb(255, 241, 234))
       }
       else{
           holder.itemView.findViewById<MaterialCardView>(R.id.mood_card).setCardBackgroundColor(Color.rgb(66, 67, 95))
       }
       holder.itemView.findViewById<MaterialCardView>(R.id.mood_card).setOnClickListener{
           if(position==moodSelected){
                moodId = 0
                //moodId = item.id
                moodSelected = -1
               notifyDataSetChanged()

           }
           else{
               moodId = item.id
               moodSelected = position
               notifyDataSetChanged()

               //holder.itemView.findViewById<MaterialCardView>(R.id.mood_card).setCardBackgroundColor(Color.rgb(66, 67, 95))
           }

       }

    }


}