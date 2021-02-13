package com.velocityappsdj.projektdawn.viewmodel

import android.app.Activity
import android.content.Context
import android.text.Layout
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velocityappsdj.projektdawn.adapters.ProjectAdapter
import com.velocityappsdj.projektdawn.databinding.ActivityMainBinding
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.repository.ProjectRepo

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"
    lateinit var projects: MutableList<Project>
    var _projects = MutableLiveData<List<Project>>()
    lateinit var projectListViewModel: ProjectListViewModel
    lateinit var projectAdapter: ProjectAdapter

    fun getProjects() {
        Log.d(TAG, "getProjects: " + ProjectRepo().getProjects().toString())
        projects = ProjectRepo().getProjects().toMutableList()
        _projects.value = projects
    }




    /* fun updateProjectList(projects: List<Project>) {
         projectListViewModels.clear()
         for (project in projects) {

             projectListViewModels.add(ProjectListViewModel(project))
         }
         projectAdapter.notifyDataSetChanged()
     }*/

    fun setUpRecyclerView(activityMainBinding: ActivityMainBinding, context: Context) {
        projectListViewModel = ProjectListViewModel(_projects)
        projectAdapter = ProjectAdapter(projectListViewModel)
        activityMainBinding.recyclerProject.layoutManager = LinearLayoutManager(context)
        activityMainBinding.recyclerProject.adapter = projectAdapter

    }

    /* fun addProject(project: Project) {
         projectListViewModels.add(ProjectListViewModel(project))
         projectAdapter.notifyDataSetChanged()
     }*/

    fun setChangeListener(activity: Activity){
        _projects.observe(, Observer {

        })
    }


    fun launchBottomSheet() {
        Log.d(TAG, "launchBottomSheet() called")
        projects.add(Project("SwordFish"))
        _projects.postValue(projects)
        Log.d(TAG, "launchBottomSheet() called" + projects[0].toString())
        projectAdapter.notifyDataSetChanged()

    }

}