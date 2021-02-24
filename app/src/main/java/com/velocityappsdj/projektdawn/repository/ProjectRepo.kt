package com.velocityappsdj.projektdawn.repository

import androidx.lifecycle.MutableLiveData
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.util.FirebaseDBUtil
import com.velocityappsdj.projektdawn.util.Resource

class ProjectRepo {

    fun getProjectList(projectsListLiveData: MutableLiveData<Resource<List<Project>>>) {
        FirebaseDBUtil.getProjects(projectsListLiveData)
    }

    fun addProject(project: Project, projectLiveData: MutableLiveData<Resource<Project>>) {
        FirebaseDBUtil.addProject(project, projectLiveData)
    }

    fun getProjectById(projectId: String, projectLiveData: MutableLiveData<Resource<Project>>) {
        FirebaseDBUtil.getProjectById(projectId, projectLiveData)
    }
}