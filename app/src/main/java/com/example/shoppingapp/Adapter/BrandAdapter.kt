package com.example.shoppingapp.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.Model.BrandModel
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.ViewholderBrandBinding

class BrandAdapter(private val context: Context, val items:MutableList<BrandModel>):RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    private var selectedPosition =-1
    private var lastSelectedPosition = -1

    class ViewHolder(val binding:ViewholderBrandBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.ViewHolder {
        val binding= ViewholderBrandBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            val currentPosition = holder.adapterPosition
            if (currentPosition != RecyclerView.NO_POSITION) {
                lastSelectedPosition = selectedPosition
                selectedPosition = currentPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }

        if (selectedPosition == position) {
            holder.binding.pic.setBackgroundColor(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.white))
            )
            holder.binding.title.setTextColor(context.getColor(R.color.white))
            holder.binding.title.visibility = View.VISIBLE
        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundColor(0)
            ImageViewCompat.setImageTintList(
                holder.binding.pic,
                ColorStateList.valueOf(context.getColor(R.color.black))
            )
            holder.binding.title.setTextColor(context.getColor(R.color.black))
            holder.binding.title.visibility = View.GONE
        }
    }


    override fun getItemCount(): Int = items.size


}