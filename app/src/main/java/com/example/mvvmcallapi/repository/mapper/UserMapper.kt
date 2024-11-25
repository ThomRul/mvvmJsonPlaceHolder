package com.example.mvvmcallapi.repository.mapper

import com.example.mvvmcallapi.model.User
import com.example.mvvmcallapi.repository.dto.CreateUserDTO
import com.example.mvvmcallapi.repository.dto.UserDTO

object UserMapper {
    // Conversion DTO vers Model
    fun toUser(dto: UserDTO) = User(
        id = dto.id,
        name = dto.name,
        email = dto.email
    )

    // Conversion Model vers DTO pour création
    fun toCreateDTO(user: User) = CreateUserDTO(
        name = user.name,
        email = user.email
    )
}