package com.velocityappsdj.projektdawn.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.velocityappsdj.projektdawn.model.Project
import com.velocityappsdj.projektdawn.model.User
import com.velocityappsdj.projektdawn.util.FirebaseDBUtil.reference
import java.util.*
import kotlin.collections.ArrayList

object FirebaseDBUtil {
    private const val TAG = "FirebaseDBUtil"
    val PROJECTS = "projects";

    var reference = Firebase.database.reference.child("users")
    var auth = FirebaseAuth.getInstance()

    //<editor-fold desc="Add">
    fun addUser(user: User) {
        if (user.id != null) {
            reference.child(user.id!!).get().addOnSuccessListener {
                if (!it.exists()) {
                    reference.child(user.id!!).setValue(user)
                }
            }
        } else {
            handleUserNotFound()
        }
    }

    private fun handleUserNotFound() {

    }

    fun addProject(project: Project, projectLiveData: MutableLiveData<Resource<Project>>) {

        var uid = auth.currentUser?.uid
        Log.d(TAG, "addProject: $project")

        if (uid != null) {
            var key = reference.child(uid).child(PROJECTS).push().key
            project.id = key
            val post = project.toMap()
            Log.d(TAG, "addProject: $post")
            val childUpdates = hashMapOf<String, Any>(
                "$uid/$PROJECTS/$key" to post,
                "$PROJECTS/$uid/$key" to post
            )

            reference.updateChildren(childUpdates).addOnFailureListener {
                val msg = "Can't write data"
                Log.e(TAG, "addProject: $msg")
                projectLiveData.postValue(Resource.error(msg, project))
            }.addOnSuccessListener {
                projectLiveData.postValue(Resource.success(project))
            }
        } else {
            handleUserNotFound()
        }
    }
    //</editor-fold>

    //<editor-fold desc="Get">
    fun getProjects(projectsListLiveData: MutableLiveData<Resource<List<Project>>>) {
        Log.d(TAG, "getProjects() called with: projectsListLiveData = $projectsListLiveData")

        var uid = auth.currentUser?.uid
        var projects = ArrayList<Project>()

        if (uid != null) {
            reference.child(uid).child(PROJECTS).addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    Log.d(
                        TAG,
                        "onChildAdded() called with: snapshot = $snapshot, previousChildName = $previousChildName"
                    )
                    var project = snapshot.getValue(Project::class.java)
                    projects.add(project!!)

                    projectsListLiveData.postValue(Resource.success(projects))
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    var project = snapshot.getValue(Project::class.java)

                    if (project != null) {
                        var pos = projects.indexOfFirst { it.id == project?.id }

                        if (pos != -1) {
                            projects.removeAt(pos)
                        }
                        projects.add(pos, project!!)
                        projectsListLiveData.postValue(Resource.success(projects))
                    }

                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    Log.d(TAG, "onChildRemoved() called with: snapshot = $snapshot")

                    projects.remove(snapshot.getValue(Project::class.java))
                    projectsListLiveData.postValue(Resource.success(projects!!))
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    TODO("Not yet implemented")
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        } else {
            handleUserNotFound()
        }
    }

    fun getProjectById(projectId: String, projectLiveData: MutableLiveData<Resource<Project>>) {

        var uid = auth.currentUser?.uid
        if (uid != null) {
            reference.child(PROJECTS).child(uid).child(projectId)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var project = snapshot.getValue(Project::class.java)
                        if (project != null) {
                            projectLiveData.postValue(Resource.success(project))
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        projectLiveData.postValue(
                            Resource.error(
                                "Error loading project data",
                                Project()
                            )
                        )
                    }

                })
        } else {
            handleUserNotFound()
        }

    }
    //</editor-fold>
}




