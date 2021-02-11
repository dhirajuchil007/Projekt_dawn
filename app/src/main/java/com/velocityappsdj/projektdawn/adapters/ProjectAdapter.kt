package com.velocityappsdj.projektdawn.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.databinding.ProjectListItemBinding
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.viewmodel.ProjectListViewModel

class ProjectAdapter(var projectListViewModel: ProjectListViewModel) :
    RecyclerView.Adapter<ProjectAdapter.MyViewHolder>() {

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

        holder.bind(projectListViewModel.getProjectsList()[position])

    }

    override fun getItemCount(): Int {
        return projectListViewModel.getProjectsList().size
    }
}