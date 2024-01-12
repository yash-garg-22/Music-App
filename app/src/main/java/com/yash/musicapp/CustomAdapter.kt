package com.yash.musicapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items.view.*


class CustomAdapter(private val mList: List<Data>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.items, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.title.text = ItemsViewModel.title_short
        Picasso.get()
            .load(ItemsViewModel.album.cover)
            .into(holder.image)

        holder.button.setOnClickListener {
            val intent = Intent(holder.itemView.context, Playing::class.java)
            intent.putExtra("songPhoto", ItemsViewModel.album.cover)
            intent.putExtra("link", ItemsViewModel.preview)
            intent.putExtra("title", ItemsViewModel.title_short)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val image: ImageView = itemView.image
        val title: TextView = itemView.title
        val button: Button = itemView.play
    }
}