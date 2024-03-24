package com.example.randompet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PetAdapter (petInList : List<String>): RecyclerView.Adapter<PetAdapter.ViewHolder>() {

    private val petList: List<String> = petInList;
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage : ImageView

        init {
            petImage = view.findViewById(R.id.petImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return petList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(petList[position])
            .centerCrop()
            .into(holder.petImage)

        holder.petImage.setOnClickListener {
            var toast = Toast.makeText(holder.itemView.context, "Dog at position $position clicked.", 500)
            toast.show()
        }
    }
}