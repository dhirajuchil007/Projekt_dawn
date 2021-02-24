package com.velocityappsdj.projektdawn.repository

import com.velocityappsdj.projektdawn.model.User
import com.velocityappsdj.projektdawn.util.FirebaseDBUtil

class LoginRepo {

    fun addUser(user: User) {
        FirebaseDBUtil.addUser(user)
    }
}