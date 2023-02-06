package com.mechbuddy.mechbuddyadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mechbuddy.mechbuddyadmin.R
import com.mechbuddy.mechbuddyadmin.databinding.ItemCategoryLayoutBinding
import com.mechbuddy.mechbuddyadmin.model.CategoryModel

class CategoryAdapter: RecyclerView.Adapter<CategoryViewHolder>(){

    private val arraylist = ArrayList<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_layout,parent,false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.textView.text=arraylist[position].cat
        Glide.with(holder.imageView.context).load(arraylist[position].img).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    fun updateItems(list: ArrayList<CategoryModel>) {
        arraylist.clear()
        arraylist.addAll(list)
        notifyDataSetChanged()
    }

}

class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val textView = itemView.findViewById<TextView>(R.id.textView)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView2)
}