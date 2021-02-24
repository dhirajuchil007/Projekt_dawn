package com.velocityappsdj.projektdawn.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.repository.ProjectRepo
import com.velocityappsdj.projektdawn.util.Resource
import com.velocityappsdj.projektdawn.util.SingleLiveEvent

class AddProjectViewModel : ViewModel() {
    private val TAG = "AddProjectViewModel"
    var addProjectLiveData = SingleLiveEvent<Resource<Project>>()
    var newProjectName: String = ""
    var summary: String = ""
    var isProjectNameEmpty = SingleLiveEvent<Boolean>()

    fun addNewItem() {
        Log.d(TAG, "addNewItem() called")
        if (newProjectName.isEmpty()) {
            isProjectNameEmpty.postValue(true)
        } else {
            isProjectNameEmpty.postValue(false)
            ProjectRepo().addProject(Project("", newProjectName, summary), addProjectLiveData)
        }
    }

}