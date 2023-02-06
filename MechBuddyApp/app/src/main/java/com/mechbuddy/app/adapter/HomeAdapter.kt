package com.mechbuddy.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mechbuddy.app.R
import com.mechbuddy.app.datamodel.ProductModel

class HomeAdapter: RecyclerView.Adapter<ProductViewHolder>() {
    private val arraylist = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_image_view, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.textView.text = arraylist[position].cat
        Glide.with(holder.itemView.context).load(arraylist[position].img).placeholder(R.drawable.cart).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    fun updateItems(list: ArrayList<ProductModel>){
        arraylist.clear()
        arraylist.addAll(list)

        notifyDataSetChanged()
    }
}


class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val textView = itemView.findViewById<TextView>(R.id.textView)
    val imageView = itemView.findViewById<ImageView>(R.id.imageView)
}