package com.example.mvvmcallapi.repository.interfaces

import com.example.mvvmcallapi.repository.dto.CreateUserDTO
import com.example.mvvmcallapi.repository.dto.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IUserApi {
    @GET("users")
    // https://jsonplaceholder.typicode.com/users
    suspend fun getUsers() : Response<List<UserDTO>>

    @POST("users")
    suspend fun createUser(@Body user : CreateUserDTO) : Response<UserDTO>
}