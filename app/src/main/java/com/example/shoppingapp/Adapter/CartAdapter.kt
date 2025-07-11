package com.example.shoppingapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shoppingapp.Helper.ChangeNumberItemsListener
import com.example.shoppingapp.Helper.ManagmentCart
import com.example.shoppingapp.Model.ItemModel
import com.example.shoppingapp.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<ItemModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?=null
):RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderCartBinding):RecyclerView.ViewHolder(binding.root){

    }

    private val managmentCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int= listItemSelected.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text=item.title
        holder.binding.feeEachItem.text="$${item.price}"
        holder.binding.totalEachItem.text="$${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text= item.numberInCart.toString()


        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)

        holder.binding.plusCartBtn.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                managmentCart.plusItem(listItemSelected, pos, object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
            }
        }

        holder.binding.minusCartBtn.setOnClickListener {
            val pos = holder.adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                managmentCart.minusItem(listItemSelected, pos, object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
            }
        }

    }
}