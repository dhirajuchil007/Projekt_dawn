package com.velocityappsdj.projektdawn.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.MaterialToolbar
import com.velocityappsdj.projektdawn.R
import com.velocityappsdj.projektdawn.databinding.ActivityAddProjectBinding
import com.velocityappsdj.projektdawn.viewmodel.AddProjectViewModel
import kotlinx.android.synthetic.main.project_name_bottom_sheet.*
import kotlinx.android.synthetic.main.toolbar.view.*

class AddProject : AppCompatActivity() {
    lateinit var binding: ActivityAddProjectBinding
    lateinit var addProjectViewModel: AddProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        init()
    }

    private fun init() {
        setUpBinding()
        setupToolBar()
    }

    private fun setUpBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_project)
        binding.lifecycleOwner = this

        addProjectViewModel = ViewModelProviders.of(this).get(AddProjectViewModel::class.java)

        binding.viewmodel = addProjectViewModel

        addProjectViewModel.isProjectNameEmpty.observe(this, Observer {
            if (it) {
                binding.edtNewProjectName.error = getString(R.string.error_enter_project_name)
            } else
                binding.edtNewProjectName.error = null
        })

        addProjectViewModel.addProjectLiveData.observe(this, Observer {
            if (it.message != null) {

            } else {
                finish()
            }
        })

        showKeyboard()

    }

    private fun showKeyboard() {
        binding.edtNewProjectName.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

    }

    private fun setupToolBar() {

        val toolbar = binding.toolbar.root as MaterialToolbar
        toolbar.txtTitle.text = getString(R.string.add_project)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
}