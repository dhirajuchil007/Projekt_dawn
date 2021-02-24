package com.velocityappsdj.projektdawn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.databinding.ProjectListItemBinding
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.viewmodel.ProjectListViewModel

class ProjectAdapter(var projectList: List<Project>, val listener: ProjectClickListener) :
    RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {
    interface ProjectClickListener {
        fun onClick(project: Project);
    }

    class MyViewHolder(private var projectListItemBinding: ProjectListItemBinding) :
        RecyclerView.ViewHolder(projectListItemBinding.root) {
        fun bind(project: Project) {
            projectListItemBinding.project = project
            projectListItemBinding.executePendingBindings()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var projectListItemBinding: ProjectListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.project_list_item, parent, false
        )

        return MyViewHolder(projectListItemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val project = projectList[position]
        holder.bind(project)
        holder.itemView.setOnClickListener {
            listener.onClick(project)
        }

    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}