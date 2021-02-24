package com.velocityappsdj.projektdawn.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.adapters.ProjectAdapter
import com.velocityappsdj.projektdawn.databinding.ActivityMainBinding
import com.velocityappsdj.projektdawn.databinding.ProjectListItemBinding
import com.velocityappsdj.projektdawn.databinding.ProjectNameBottomSheetBinding
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.viewmodel.MainViewModel
import com.velocityappsdj.projektdawn.viewmodel.ProjectListViewModel
import kotlinx.android.synthetic.main.toolbar.view.*

class MainActivity() : AppCompatActivity() {

    companion object {
        val PROJECT_ID: String = "projectid"
    }

    private val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        init()
        //initBottomSheet()


    }

    /* private fun initBottomSheet() {

         bottomSheetDialog = BottomSheetDialog(this, R.style.DialogStyle)
         bottomSheetBinding = DataBindingUtil.inflate(
             LayoutInflater.from(this),
             R.layout.project_name_bottom_sheet,
             findViewById(R.id.addProjectContainer),
             false
         )
         bottomSheetDialog.setContentView(bottomSheetBinding.root)
         bottomSheetBinding.mainViewModel = mainViewModel
         bottomSheetBinding.edtNewProjectName.requestFocus()
         mainViewModel.isProjectNameEmpty.observe(this, Observer {
             if (it) bottomSheetBinding.edtNewProjectName.error =
                 resources.getText(R.string.error_enter_project_name)
             else
                 bottomSheetBinding.edtNewProjectName.error = null
         })


     }*/

    private fun init() {
        setupDataBinding()
        setupToolbar()
    }

    private val listener = object : ProjectAdapter.ProjectClickListener {
        override fun onClick(project: Project) {
            var intent = Intent(this@MainActivity, ProjectActivity::class.java)
            intent.putExtra(PROJECT_ID, project.id)
            startActivity(intent)
        }
    }

    private fun setupDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getProjects()
        mainViewModel.projectsListLiveData.observe(this, Observer {
            Log.d(TAG, "setupDataBinding() called")

            setUpRecyclerView(it.data)
        })
        mainViewModel.launchAddProjectActivity.observe(this, Observer {
            if (it) {
                startAddProjectActivity()
            }
        })
        binding.mainViewModel = mainViewModel
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar.root as MaterialToolbar
//        toolbar.title = getString(R.string.projects)
        toolbar.txtTitle.text = getString(R.string.projects)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    private fun startAddProjectActivity() {
        var intent = Intent(this, AddProject::class.java)
        startActivity(intent)

    }

    override fun onBackPressed() {
        FirebaseAuth.getInstance().signOut()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setUpRecyclerView(projects: List<Project>) {

        Log.d(TAG, "setUpRecyclerView() called with: projects = $projects")

        var projectAdapter = ProjectAdapter(projects, listener)
        binding.recyclerProject.layoutManager = LinearLayoutManager(this)
        binding.recyclerProject.adapter = projectAdapter


    }
}