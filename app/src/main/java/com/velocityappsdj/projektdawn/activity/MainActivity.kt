package com.velocityappsdj.projektdawn.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.databinding.ActivityMainBinding
import com.velocityappsdj.projektdawn.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        init()
    }

    private fun init() {

        mainViewModel.setUpRecyclerView(binding, this)
        mainViewModel.getProjects()


    }


}