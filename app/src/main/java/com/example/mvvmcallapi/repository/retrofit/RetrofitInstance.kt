package com.example.mvvmcallapi.repository.retrofit

import com.example.mvvmcallapi.repository.interfaces.IUserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Avec by lazy :
    // L'instance Retrofit n'est créée que lors de la première utilisation de api
    val api: IUserApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Ajoute un convertisseur JSON
            // Gson va automatiquement :
            // - Convertir JSON → DTOs (quand on reçoit une réponse)
            // - Convertir DTOs → JSON (quand on envoie une requête)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // Crée une implémentation de notre interface
            // Retrofit va automatiquement générer le code pour faire les requêtes HTTP
            .create(IUserApi::class.java)
    }
}