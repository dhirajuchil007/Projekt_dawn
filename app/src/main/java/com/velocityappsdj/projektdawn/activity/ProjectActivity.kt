package com.velocityappsdj.projektdawn.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.MaterialToolbar
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.databinding.ActivityProjectBinding
import com.velocityappsdj.projektdawn.util.Resource
import com.velocityappsdj.projektdawn.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.toolbar.view.*

class ProjectActivity : AppCompatActivity() {
    lateinit var binding: ActivityProjectBinding
    lateinit var projectViewModel: ProjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setupDataBinding()
        setUpToolBar()
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)
        binding.lifecycleOwner = this

        projectViewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)
        projectViewModel.getProject(getProjectId())
        projectViewModel.projectLiveData.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    setUpToolBarTitle(it.data.name!!)
                }
            }
        })
        binding.viewmodel = projectViewModel

    }

    private fun getProjectId(): String {
        if (intent.hasExtra(MainActivity.PROJECT_ID)) {
            val stringExtra = intent.getStringExtra(MainActivity.PROJECT_ID)
            if (stringExtra != null) {
                return stringExtra
            }
        }

        return ""
    }

    private fun setUpToolBarTitle(name: String) {
        val toolbar = binding.toolbar.root as MaterialToolbar
        toolbar.txtTitle.text = name
    }

    private fun setUpToolBar() {
        val toolbar = binding.toolbar.root as MaterialToolbar
        toolbar.title = getString(R.string.projects)
        toolbar.txtTitle.text = getString(R.string.projects)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
}