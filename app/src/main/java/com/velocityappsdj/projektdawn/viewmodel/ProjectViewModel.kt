package com.velocityappsdj.projektdawn.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.repository.ProjectRepo
import com.velocityappsdj.projektdawn.util.Resource
import com.velocityappsdj.projektdawn.util.SingleLiveEvent

class ProjectViewModel : ViewModel() {
    var projectLiveData = MutableLiveData<Resource<Project>>()
    var addProjectItemEvent = SingleLiveEvent<Boolean>()
    fun getProject(projectId: String) {
        ProjectRepo().getProjectById(projectId, projectLiveData)
    }

    fun addProjectItem() {
        addProjectItemEvent.postValue(true)
    }
}