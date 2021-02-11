package com.velocityappsdj.projektdawn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.velocityappsdj.projektdawn.model.Project

class ProjectListViewModel(mutableLiveData: MutableLiveData<List<Project>>) : ViewModel() {
    private val _projects = mutableLiveData

    private val projects: LiveData<List<Project>> = _projects

    fun getProjectsList(): List<Project> {
        if (projects.value != null) {
            return projects.value!!
        }

        return emptyList()
    }

}