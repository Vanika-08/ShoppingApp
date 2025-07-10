package com.example.shoppingapp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shoppingapp.Adapter.BrandAdapter
import com.example.shoppingapp.Adapter.PopularAdapter
import com.example.shoppingapp.Model.SliderModel
import com.example.shoppingapp.Adapter.SliderAdapter
import com.example.shoppingapp.ViewModel.MainViewModel
import com.example.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()

        initBrand()

        initPopular()

    }

        private fun initBanner(){
            binding.progressBarBanner.visibility= View.VISIBLE
            viewModel.banners.observe(this) { items ->
                banners(items)
                binding.progressBarBanner.visibility = View.GONE
            }
            viewModel.loadBanners()
        }

        private fun banners(images: List<SliderModel>) {
            binding.viewpagerSlider.adapter =
                SliderAdapter(this, images.toMutableList(), binding.viewpagerSlider)

            binding.viewpagerSlider.clipToPadding = false
            binding.viewpagerSlider.clipChildren = false
            binding.viewpagerSlider.offscreenPageLimit = 3
            binding.viewpagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            binding.viewpagerSlider.setPageTransformer(transformer)

            if (images.size > 1) {
                binding.dotindicator.visibility = View.VISIBLE
                binding.dotindicator.attachTo(binding.viewpagerSlider)
            }
        }


    private fun initBrand(){
        binding.progressBarBrand.visibility= View.VISIBLE
        viewModel.brands.observe(this) {brandList ->
            binding.viewBrand.layoutManager=LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.viewBrand.adapter = BrandAdapter(this@MainActivity, brandList.toMutableList())
            binding.progressBarBrand.visibility= View.GONE
        }
        viewModel.loadBrands()
    }

    private fun initPopular(){
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.populars.observe(this){popularList ->
            binding.viewPopular.layoutManager= GridLayoutManager(this@MainActivity, 2)
            binding.viewPopular.adapter=PopularAdapter(this@MainActivity, popularList.toMutableList())
            binding.progressBarPopular.visibility=View.GONE
        }
        viewModel.loadPopulars()
    }

}