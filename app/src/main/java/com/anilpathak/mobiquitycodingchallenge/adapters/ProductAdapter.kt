package com.anilpathak.mobiquitycodingchallenge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anilpathak.mobiquitycodingchallenge.R
import com.anilpathak.mobiquitycodingchallenge.retorfit.model.Category
import com.anilpathak.mobiquitycodingchallenge.retorfit.model.Product
import kotlin.properties.Delegates

class ProductAdapter : ListAdapter<Category, ProductAdapter.ViewHolder>(ProductsDiffUtil()) {

    var data by Delegates.observable(emptyList<Category>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Category) {
            val product = itemView.findViewById<TextView>(R.id.txt_product_name)
            product.text = item.name

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.product_items, parent, false)
                return ViewHolder(view)
            }
        }

    }

    class ProductsDiffUtil : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

}