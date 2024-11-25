package com.example.mvvmcallapi.repository.dto

data class CreateUserDTO(
    val name: String,
    val email: String,
    val username: String = ""
)