package com.velocityappsdj.projektdawn.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velocityappsdj.projektdawn.adapters.ProjectAdapter
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.repository.ProjectRepo
import com.velocityappsdj.projektdawn.util.Resource
import com.velocityappsdj.projektdawn.util.SingleLiveEvent

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"
    lateinit var projects: MutableList<Project>
    lateinit var projectListViewModel: ProjectListViewModel
    lateinit var projectAdapter: ProjectAdapter
    var newProjectName: String = ""

    var projectsListLiveData = MutableLiveData<Resource<List<Project>>>()

    val launchAddProjectActivity = SingleLiveEvent<Boolean>()


    fun getProjects() {
        //Log.d(TAG, "getProjects: " + ProjectRepo().getProjects().toString())
       ProjectRepo().getProjectList(projectsListLiveData)

    }


    /* fun updateProjectList(projects: List<Project>) {
         projectListViewModels.clear()
         for (project in projects) {

             projectListViewModels.add(ProjectListViewModel(project))
         }
         projectAdapter.notifyDataSetChanged()
     }*/


    /* fun addProject(project: Project) {
         projectListViewModels.add(ProjectListViewModel(project))
         projectAdapter.notifyDataSetChanged()
     }*/


    fun launchBottomSheet() {
        Log.d(TAG, "launchBottomSheet() called")
        launchAddProjectActivity.postValue(true)

    }


}