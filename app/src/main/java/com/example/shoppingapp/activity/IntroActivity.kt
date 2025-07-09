package com.example.shoppingapp.activity

import android.content.Intent
import android.os.Bundle
import com.example.shoppingapp.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getStartBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}