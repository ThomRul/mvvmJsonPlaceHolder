package com.example.mvvmcallapi.repository

import android.util.Log
import com.example.mvvmcallapi.model.User
import com.example.mvvmcallapi.repository.mapper.UserMapper
import com.example.mvvmcallapi.repository.retrofit.RetrofitInstance

class UserRepository {
    private val api = RetrofitInstance.api

    //getAll
    suspend fun getUsers() : List<User> {
        return try {
            val response = api.getUsers()
            if (response.isSuccessful) {
                // si la response body n'est pas null on le map pour transformer chaque element de la réponse en un objet User
                // ?: operateur elvis si la reponse est null, on retourne une liste vide
                response.body()?.map { user -> UserMapper.toUser(user) } ?: emptyList()
            } else {
                Log.e("API_CALL", "Erreur: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API_CALL", "Erreur: ${e.message}")
            emptyList()
        }
    }

    //create
    suspend fun createUser(user: User) : User?{
        return try{
            //conversion en dto, appel de l'api et reconversion en model
            val dto = UserMapper.toCreateDTO(user)
            val response = api.createUser(dto)
            Log.d("API_CALL", "Code: ${response.code()}")
            Log.d("API_CALL", "Réponse: ${response.body()}")
            if (response.isSuccessful) {  // code 200-299
                response.body()?.let { UserMapper.toUser(it) }
            } else {
                Log.e("API_CALL", "Erreur: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_CALL", "Erreur: ${e.message}")
            null
        }
    }
}