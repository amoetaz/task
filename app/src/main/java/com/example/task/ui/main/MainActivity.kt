package com.example.task.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.adapters.RecipesListAdapter
import com.example.task.databinding.ActivityMainBinding
import com.example.task.helper.SortType
import com.example.task.helper.Utils
import com.example.task.models.Recipe
import com.example.task.repository.local.SessionManager
import com.rehabtech.rtech.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: RecipesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        title = "Recipes"
        setupAdapter()
        if (Utils.checkInternetConnection(this)) {
            viewModel.getRecipes()
        } else {//get data form local
            val json = SessionManager.getData()
            if (json != "") {
                val data = Utils.convertJsonToArrayOfObjects<ArrayList<Recipe>>(json!!)
                adapter.setData(data)
            }else{
                Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show()
            }
        }
        observeData()
    }

    private fun setupAdapter() {
        adapter = RecipesListAdapter(ArrayList(), this)
        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu , menu)
        val searchItem = menu!!.findItem(R.id.search)
        val seachview = MenuItemCompat.getActionView(searchItem) as SearchView
        seachview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                 seachview.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText!!)
                return false
             }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sort_by_fat ->{
                adapter.sortBy(SortType.fat)
                SessionManager.setSelectedSort(SortType.fat.name)
                return true
            }

            R.id.sort_by_calories ->{
                adapter.sortBy(SortType.calories)
                SessionManager.setSelectedSort(SortType.calories.name)
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
    private fun observeData() {
        viewModel.isLoading.observe(this, Observer {

            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        })

        viewModel.errorInt.observe(this, Observer {

            Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
        })

        viewModel.recipeResponse.observe(this, Observer {

            adapter.setData(it)
            adapter.sortBy(SortType.valueOf(SessionManager.getSelectedSort()!!))
            SessionManager.setData(Utils.convertObjectToJson(it))
        })
    }
}