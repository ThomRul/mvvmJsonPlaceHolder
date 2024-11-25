package com.example.mvvmcallapi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcallapi.model.User
import com.example.mvvmcallapi.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    // Etat observable pour la liste des users
    private val _users = MutableLiveData<List<User>>()
    val user : LiveData<List<User>> = _users

    //Etat pour les erreurs
    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    //get all
    fun loadUsers(){
        viewModelScope.launch {
            try{
                _users.value = userRepository.getUsers()
            }catch (e : Exception){
                _error.value = e.message

            }
        }
    }

    // create
    fun createUser(name: String, email: String) {
        viewModelScope.launch {  // Gestion async avec coroutine
            try {
                val newUser = User(0, name, email)  // id = 0 car généré par l'API
                userRepository.createUser(newUser)?.let {
                    // Recharge la liste après création réussie
                    loadUsers()
                }
            } catch (e: Exception) {
                _error.value = "Erreur lors de la création: ${e.message}"
            }
        }
    }

}