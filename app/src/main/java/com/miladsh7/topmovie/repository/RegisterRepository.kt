package com.miladsh7.topmovie.repository

import com.miladsh7.topmovie.api.ApiServices
import com.miladsh7.topmovie.model.register.BodyRegister
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val api: ApiServices
) {
    suspend fun registerUser(body: BodyRegister) = api.registerUser(body)

}