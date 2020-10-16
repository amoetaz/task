package com.example.task.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.ActivityDetailBinding
import com.example.task.helper.Utils
import com.example.task.models.Recipe

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var recipe : Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_detail)

        initUI()
    }

    //get data from intent and publish it to ui
    private fun initUI() {
        if (intent.hasExtra("recipe")){
            recipe = Utils.convertJsonToObject(intent.getStringExtra("recipe")!!)
            title = recipe.name
            Glide.with(this).load(recipe.image).into(binding.ivImage)
            binding.tvDesc.text = recipe.description
            binding.tvFats.text = recipe.fats
            binding.tvCalories.text = recipe.calories
        }
    }
}