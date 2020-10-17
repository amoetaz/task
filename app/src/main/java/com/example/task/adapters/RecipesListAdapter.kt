package com.example.task.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.helper.SortType
import com.example.task.helper.Utils
import com.example.task.helper.caloriesToInt
import com.example.task.helper.fatsToInt
import com.example.task.models.Recipe
import com.example.task.ui.DetailActivity
import kotlinx.android.synthetic.main.recipe_item.view.*
import java.util.*

import kotlin.collections.ArrayList


class RecipesListAdapter(val recipes: ArrayList<Recipe>, val context: Context) :
    RecyclerView.Adapter<RecipesListAdapter.MyViewHolder>() {


    val originaRecipes: ArrayList<Recipe> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_item, parent, false)
        )
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val recipe = recipes[position]
        Glide.with(context).load(recipe.image).into(holder.image)

        holder.name.text = recipe.name

        setAnimation(position, holder)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("recipe", Utils.convertObjectToJson(recipe))
            context.startActivity(intent)
        }
    }

    fun sortBy(type: SortType) =
        when (type) {
            SortType.fat -> {
                val sortedList = recipes.sortedWith(compareBy { it.fatInt })
                setData(sortedList)

            }
            SortType.calories -> {
                val sortedList = recipes.sortedWith(compareBy { it.caloriesInt })
                setData(sortedList)
            }
            else -> {
                setData(recipes)
            }
        }


    fun clearData() {
        if (recipes.size > 0) {
            recipes.clear()
        }
    }


    fun filter(name: String) {
        if (name != "") {
            val filter =
                recipes.filter { recipe -> recipe.name.toLowerCase().contains(name.toLowerCase()) }
            clearData()
            recipes.addAll(filter as ArrayList<Recipe>)
            notifyDataSetChanged()
        } else {
            setData(originaRecipes)
        }
    }

    // set animation slide left and right
    fun setAnimation(position: Int, holder: MyViewHolder) {
        val slideLeft = AnimationUtils.loadAnimation(context, R.anim.slide_right)
        val slideright = AnimationUtils.loadAnimation(context, R.anim.slide_left)
        if (position % 2 == 0)
            holder.itemView.startAnimation(slideLeft)
        else
            holder.itemView.startAnimation(slideright)

    }

    fun setData(data: List<Recipe>) {
        clearData()
        recipes.addAll(data)
        originaRecipes.addAll(recipes)
        this.notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.iv_image
        val name = view.tv_name
    }

}