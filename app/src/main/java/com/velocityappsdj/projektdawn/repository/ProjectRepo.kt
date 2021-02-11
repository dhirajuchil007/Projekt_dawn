package com.velocityappsdj.projektdawn.repository

import com.velocityappsdj.projektdawn.model.Project

class ProjectRepo {

    fun getProjects(): List<Project> {
        return listOf(Project("Sailfish"), Project("Salmon"),Project("CuttleFish"))
    }
}