package com.example.shoppingapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.shoppingapp.Model.SliderModel
import com.example.shoppingapp.R

class SliderAdapter(private val context: Context,private var sliderItems:List<SliderModel>, private val viewPager2: ViewPager2): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val runnable = Runnable {
        if (sliderItems.isNotEmpty()) {
            notifyItemChanged(sliderItems.lastIndex)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container,parent,false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
       holder.setImage(sliderItems[position])
        if(position==sliderItems.lastIndex-1){
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int = sliderItems.size

    class SliderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val imageView:ImageView=itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItems: SliderModel){
            android.util.Log.d("GLIDE_LOG", "Binding image at position  ${sliderItems.url}")
            val requestOptions = RequestOptions().transform(CenterInside())

            Glide.with(itemView.context)
                .load(sliderItems.url)
                .apply(requestOptions)
                .into(imageView)
        }
    }

}